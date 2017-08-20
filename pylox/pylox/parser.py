from typing import List

from .expr import ExprAssign, ExprBinary, ExprCall, ExprGet, ExprGrouping, ExprLiteral, ExprLogical, ExprSet, \
    ExprSuper, ExprThis, ExprUnary, ExprVariable
from .interface import ParserInterface
from .stmt import AbstractStmt, StmtBlock, StmtClass, StmtExpression, StmtFunction, StmtIf, StmtPrint, StmtReturn, \
    StmtVar, StmtWhile
from .token import Token, TokenType


class ParseError(RuntimeError):
    pass


class Parser(ParserInterface):
    def __init__(self, tokens: List[Token]):
        self._tokens = tokens
        self._current = 0

    def parse(self) -> List[AbstractStmt]:
        statements = []  # type: List[AbstractStmt]
        limiter = 0
        while not self._is_at_end() and limiter < 100:
            statements.append(self._declaration())
            limiter += 1
        return statements

    def _declaration(self):
        try:
            if self._match(TokenType.CLASS):
                return self._class_declaration()
            if self._match(TokenType.FUN):
                return self._function_declaration('function')
            if self._match(TokenType.VAR):
                return self._var_declaration()

            return self._statement()
        except ParseError:
            self._synchronize()
            return None

    def _class_declaration(self):
        name = self._consume(TokenType.IDENTIFIER, "Expect class name.")
        superclass = None
        if self._match(TokenType.LESS):
            self._consume(TokenType.IDENTIFIER, "Expect superclass name.")
            superclass = ExprVariable(self._previous())
        self._consume(TokenType.LEFT_BRACE, "Expect '{' after class name.")
        methods = []
        while (not self._check(TokenType.RIGHT_BRACE)) and not self._is_at_end():
            methods.append(self._function_declaration('method'))
        self._consume(TokenType.RIGHT_BRACE, "Expect '}' after class methods.")

        return StmtClass(name=name, superclass=superclass, methods=methods)

    def _function_declaration(self, kind: str):
        name = self._consume(TokenType.IDENTIFIER, "Expect " + kind + " name.")
        self._consume(TokenType.LEFT_PAREN, "Expect '(' after " + kind + " name.")
        parameters = []
        if not self._check(TokenType.RIGHT_PAREN):
            while True:
                if len(parameters) >= 8:
                    self._error(self._peek(), "Cannot have more than 8 parameters.")
                parameters.append(self._consume(TokenType.IDENTIFIER, "Expect parameter name."))
                if not self._match(TokenType.COMMA):
                    break
        self._consume(TokenType.RIGHT_PAREN, "Expect ')' after parameters.")
        self._consume(TokenType.LEFT_BRACE, "Expect '{' before " + kind + " body.")
        body = self._block()
        return StmtFunction(name=name, parameters=parameters, body=body)

    def _var_declaration(self):
        name = self._consume(TokenType.IDENTIFIER, "Expect variable name.")
        initializer = None
        if self._match(TokenType.EQUAL):
            initializer = self._expression()
        self._consume(TokenType.SEMICOLON, "Expect ';' after variable declaration.")

        return StmtVar(name=name, initializer=initializer)

    def _statement(self):
        if self._match(TokenType.FOR):
            return self._for_statement()
        if self._match(TokenType.IF):
            return self._if_statement()
        if self._match(TokenType.PRINT):
            return self._print_statement()
        if self._match(TokenType.RETURN):
            return self._return_statement()
        if self._match(TokenType.WHILE):
            return self._while_statement()
        if self._match(TokenType.LEFT_BRACE):
            return StmtBlock(statements=self._block())

        return self._expression_statement()

    def _for_statement(self):
        self._consume(TokenType.LEFT_PAREN, "Expect '(' after 'if'.")

        if self._match(TokenType.ELSE):
            initializer = None
        elif self._match(TokenType.VAR):
            initializer = self._var_declaration()
        else:
            initializer = self._expression_statement()

        condition = None
        if not self._check(TokenType.SEMICOLON):
            condition = self._expression()
        self._consume(TokenType.SEMICOLON, "Expect ';' after loop condition.")

        increment = None
        if not self._check(TokenType.RIGHT_PAREN):
            increment = self._expression()
        self._consume(TokenType.RIGHT_PAREN, "Expect ')' after for clauses.")

        body = self._statement()
        if increment:
            body = StmtBlock(statements=[body, StmtExpression(expression=increment)])

        if not condition:
            condition = ExprLiteral(value=True)

        body = StmtWhile(condition=condition, body=body)

        if initializer:
            body = StmtBlock(statements=[initializer, body])

        return body

    def _if_statement(self):
        self._consume(TokenType.LEFT_PAREN, "Expect '(' after 'if'.")
        condition = self._expression()
        self._consume(TokenType.RIGHT_PAREN, "Expect ')' after if condition.")

        then_branch = self._statement()
        else_branch = None
        if self._match(TokenType.ELSE):
            else_branch = self._statement()

        return StmtIf(condition=condition, then_branch=then_branch, else_branch=else_branch)

    def _print_statement(self):
        value = self._expression()
        self._consume(TokenType.SEMICOLON, "Expect ';' after value.")
        return StmtPrint(expression=value)

    def _return_statement(self):
        keyword = self._previous()
        value = None
        if not self._check(TokenType.SEMICOLON):
            value = self._expression()
        self._consume(TokenType.SEMICOLON, "Expect ';' after return value.")

        return StmtReturn(keyword=keyword, value=value)

    def _while_statement(self):
        self._consume(TokenType.LEFT_PAREN, "Expect '(' after 'while'.")
        condition = self._expression()
        self._consume(TokenType.RIGHT_PAREN, "Expect ')' after condition.")
        body = self._statement()

        return StmtWhile(condition=condition, body=body)

    def _expression_statement(self):
        expr = self._expression()
        self._consume(TokenType.SEMICOLON, "Expect ';' after expression.")
        return StmtExpression(expression=expr)

    def _block(self):
        statements = []
        while (not self._check(TokenType.RIGHT_BRACE)) and not self._is_at_end():
            statements.append(self._declaration())

        self._consume(TokenType.RIGHT_BRACE, "Expect '}' after block.")

        return statements

    def _expression(self):
        return self._assignment()

    def _assignment(self):
        expr = self._or()

        if self._match(TokenType.EQUAL):
            equals = self._previous()
            value = self._assignment()

            if isinstance(expr, ExprVariable):
                return ExprAssign(name=expr.name, value=value)
            elif isinstance(expr, ExprGet):
                return ExprSet(object=expr.object, name=expr.name, value=value)

            self._error(equals, "Invalid assignment target.")

        return expr

    def _or(self):
        expr = self._and()

        while self._match(TokenType.OR):
            operator = self._previous()
            right = self._and()
            expr = ExprLogical(left=expr, operator=operator, right=right)

        return expr

    def _and(self):
        expr = self._equality()

        while self._match(TokenType.AND):
            operator = self._previous()
            right = self._equality()
            expr = ExprLogical(left=expr, operator=operator, right=right)

        return expr

    def _equality(self):
        expr = self._comparison()

        while self._match(TokenType.BANG_EQUAL, TokenType.EQUAL_EQUAL):
            operator = self._previous()
            right = self._comparison()
            expr = ExprBinary(left=expr, operator=operator, right=right)

        return expr

    def _comparison(self):
        expr = self._addition()

        while self._match(TokenType.GREATER, TokenType.GREATER_EQUAL, TokenType.LESS, TokenType.LESS_EQUAL):
            operator = self._previous()
            right = self._addition()
            expr = ExprBinary(left=expr, operator=operator, right=right)

        return expr

    def _addition(self):
        expr = self._multiplication()

        while self._match(TokenType.MINUS, TokenType.PLUS):
            operator = self._previous()
            right = self._multiplication()
            expr = ExprBinary(left=expr, operator=operator, right=right)

        return expr

    def _multiplication(self):
        expr = self._unary()

        while self._match(TokenType.SLASH, TokenType.STAR):
            operator = self._previous()
            right = self._unary()
            expr = ExprBinary(left=expr, operator=operator, right=right)

        return expr

    def _unary(self):
        if self._match(TokenType.BANG, TokenType.MINUS):
            operator = self._previous()
            right = self._unary()
            return ExprUnary(operator=operator, right=right)

        return self._call()

    def _primary(self):
        if self._match(TokenType.FALSE):
            return ExprLiteral(value=False)
        if self._match(TokenType.TRUE):
            return ExprLiteral(value=True)
        if self._match(TokenType.NIL):
            return ExprLiteral(value=None)

        if self._match(TokenType.NUMBER, TokenType.STRING):
            return ExprLiteral(value=self._previous().literal)

        if self._match(TokenType.IDENTIFIER):
            return ExprVariable(self._previous())

        if self._match(TokenType.SUPER):
            keyword = self._previous()
            self._consume(TokenType.DOT, "Expect '.' after 'super'.")
            method = self._consume(TokenType.IDENTIFIER, "Expect superclass method name.")
            return ExprSuper(keyword=keyword, method=method)

        if self._match(TokenType.THIS):
            return ExprThis(keyword=self._previous())

        if self._match(TokenType.LEFT_PAREN):
            expr = self._expression()
            self._consume(TokenType.RIGHT_PAREN, "Expect ')' after expression.")
            return ExprGrouping(expression=expr)

        raise self._error(self._peek(), "Expect expression.")

    def _call(self):
        expr = self._primary()

        while True:
            if self._match(TokenType.LEFT_PAREN):
                expr = self._finish_call(expr)
            elif self._match(TokenType.DOT):
                name = self._consume(TokenType.IDENTIFIER, "Expect property name after '.'.")
                expr = ExprGet(object=expr, name=name)
            else:
                break

        return expr

    def _finish_call(self, callee):
        arguments = []
        if not self._check(TokenType.RIGHT_PAREN):
            while True:
                if len(arguments) >= 8:
                    self._error(self._peek(), "Cannot have more than 8 arguments.")
                arguments.append(self._expression())
                if not self._match(TokenType.COMMA):
                    break
        paren = self._consume(TokenType.RIGHT_PAREN, "Expect ')' after arguments.")

        return ExprCall(callee=callee, paren=paren, arguments=arguments)

    def _match(self, *token_types):
        for token_type in token_types:
            if self._check(token_type):
                self._advance()
                return True
        return False

    def _advance(self):
        if not self._is_at_end():
            self._current += 1
        return self._previous()

    def _consume(self, token_type, message):
        if self._check(token_type):
            return self._advance()
        raise self._error(self._peek(), message)

    def _previous(self):
        return self._tokens[self._current - 1]

    def _check(self, token_type):
        return (not self._is_at_end()) and self._peek().type == token_type

    def _is_at_end(self):
        return self._peek().type == TokenType.EOF

    def _peek(self):
        return self._tokens[self._current]

    def _synchronize(self):
        self._advance()

        while not self._is_at_end():
            if self._previous().type == TokenType.SEMICOLON:
                return

            if self._peek().type in (
                    TokenType.CLASS,
                    TokenType.FUN,
                    TokenType.VAR,
                    TokenType.FOR,
                    TokenType.IF,
                    TokenType.WHILE,
                    TokenType.PRINT,
                    TokenType.RETURN
            ):
                return

            self._advance()

    @staticmethod
    def _error(token, message):
        from . import PyLox
        PyLox.parse_error(token, message)
        return ParseError()
