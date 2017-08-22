import sys
from enum import Enum
from typing import Dict, List

from .error import PyLoxRuntimeError
from .expr import AbstractExpr, ExprAssign, ExprBinary, ExprCall, ExprGet, ExprGrouping, ExprLiteral, ExprLogical, \
    ExprSet, ExprSuper, ExprThis, ExprUnary, ExprVariable
from .interface import InterpreterInterface
from .stmt import AbstractStmt, StmtBlock, StmtClass, StmtExpression, StmtFunction, StmtIf, StmtPrint, StmtReturn, \
    StmtVar, StmtWhile
from .token import Token


class _FunctionType(Enum):
    NONE = 1
    FUNCTION = 2
    METHOD = 3
    INITIALIZER = 4


class _ClassType(Enum):
    NONE = 1
    CLASS = 2
    SUPERCLASS = 3


class Resolver(AbstractStmt.Visitor, AbstractExpr.Visitor):
    def __init__(self, interpreter: InterpreterInterface, error_file=sys.stderr):
        self._interpreter = interpreter
        self._current_function = _FunctionType.NONE  # type: _FunctionType
        self._current_class = _ClassType.NONE  # type: _ClassType
        self._scopes = _Scopes()  # type: _Scopes(List[Dict(str, bool)])
        self._error_file = error_file

    def resolve(self, statements: List[AbstractStmt]):
        for statement in statements:
            self._resolve_statement(stmt=statement)

    def _resolve_statement(self, stmt: AbstractStmt):
        stmt.accept(visitor=self)

    def _resolve_expression(self, expr: AbstractExpr):
        expr.accept(visitor=self)

    def _resolve_local(self, expr: AbstractExpr, token: Token):
        for i in range(len(self._scopes) - 1, -1, -1):
            scope = self._scopes[i]
            if token.lexeme in scope:
                self._interpreter.resolve(expr=expr, depth=len(self._scopes) - 1 - i)
                return

                # Not found. Assume it is global.

    def _resolve_function(self, function: StmtFunction, type: _FunctionType):
        enclosing_function = self._current_function
        self._current_function = type

        self._begin_scope()
        for param in function.parameters:
            self._declare(token=param)
            self._define(token=param)
        self.resolve(statements=function.body)
        self._end_scope()
        self._current_function = enclosing_function

    def _declare(self, token: Token):
        if not self._scopes:
            return

        scope = self._scopes.peek()  # type:
        if token.lexeme in scope:
            self._error(token=token,
                        message='Variable with this name already declared in this scope.',
                        error_file=self._error_file)

        scope[token.lexeme] = False

    def _define(self, token: Token):
        if not self._scopes:
            return

        self._scopes.peek()[token.lexeme] = True

    def _begin_scope(self):
        self._scopes.append({})

    def _end_scope(self):
        self._scopes.pop()

    def visit_block_stmt(self, stmt: StmtBlock):
        self._begin_scope()
        self.resolve(statements=stmt.statements)
        self._end_scope()

    def visit_class_stmt(self, stmt: StmtClass):
        self._declare(token=stmt.name)
        self._define(token=stmt.name)

        enclosing_class = self._current_class
        self._current_class = _ClassType.CLASS
        if stmt.superclass:
            self._current_class = _ClassType.SUPERCLASS
            self._resolve_statement(stmt=stmt.superclass)
            self._begin_scope()
            self._scopes.peek()['super'] = True

        for method in stmt.methods:
            self._begin_scope()
            self._scopes.peek()['this'] = True

            method_type = _FunctionType.METHOD
            if 'init' == method.name.lexeme:
                method_type = _FunctionType.INITIALIZER

            self._resolve_function(function=method, type=method_type)
            self._end_scope()

        if self._current_class == _ClassType.SUPERCLASS:
            self._end_scope()

        self._current_class = enclosing_class

    def visit_expression_stmt(self, stmt: StmtExpression):
        self._resolve_statement(stmt=stmt.expression)

    def visit_function_stmt(self, stmt: StmtFunction):
        self._declare(token=stmt.name)
        self._define(token=stmt.name)

        self._resolve_function(function=stmt, type=_FunctionType.FUNCTION)

    def visit_if_stmt(self, stmt: StmtIf):
        self._resolve_expression(expr=stmt.condition)
        self._resolve_statement(stmt=stmt.then_branch)
        if stmt.else_branch:
            self._resolve_statement(stmt=stmt.else_branch)

    def visit_print_stmt(self, stmt: StmtPrint):
        self._resolve_expression(expr=stmt.expression)

    def visit_var_stmt(self, stmt: StmtVar):
        self._declare(token=stmt.name)
        if stmt.initializer:
            self._resolve_expression(expr=stmt.initializer)
        self._define(token=stmt.name)

    def visit_return_stmt(self, stmt: StmtReturn):
        if self._current_function == _FunctionType.NONE:
            self._error(token=stmt.keyword, message='Cannot return from top-level code.', error_file=self._error_file)
        if stmt.value is not None:
            if self._current_function == _FunctionType.INITIALIZER:
                self._error(token=stmt.keyword,
                            message='Cannot return value from an initializer.',
                            error_file=self._error_file)
            self._resolve_expression(expr=stmt.value)

    def visit_while_stmt(self, stmt: StmtWhile):
        self._resolve_expression(expr=stmt.condition)
        self._resolve_statement(stmt=stmt.body)

    def visit_assign_expr(self, expr: ExprAssign):
        self._resolve_expression(expr=expr.value)
        self._resolve_local(expr=expr, token=expr.name)

    def visit_binary_expr(self, expr: ExprBinary):
        self._resolve_expression(expr=expr.left)
        self._resolve_expression(expr=expr.right)

    def visit_call_expr(self, expr: ExprCall):
        self._resolve_expression(expr=expr.callee)
        for argument in expr.arguments:
            self._resolve_expression(expr=argument)

    def visit_get_expr(self, expr: ExprGet):
        self._resolve_expression(expr=expr.object)

    def visit_grouping_expr(self, expr: ExprGrouping):
        self._resolve_expression(expr=expr.expression)

    def visit_literal_expr(self, expr: ExprLiteral):
        pass  # do nothing

    def visit_logical_expr(self, expr: ExprLogical):
        self._resolve_expression(expr=expr.left)
        self._resolve_expression(expr=expr.right)

    def visit_set_expr(self, expr: ExprSet):
        self._resolve_expression(expr=expr.object)
        self._resolve_expression(expr=expr.value)

    def visit_this_expr(self, expr: ExprThis):
        if self._current_class == _ClassType.NONE:
            self._error(token=expr.keyword,
                        message='Cannot use \'this\' outside of a class.',
                        error_file=self._error_file)
        else:
            self._resolve_local(expr=expr, token=expr.keyword)

    def visit_super_expr(self, expr: ExprSuper):
        if self._current_class == _ClassType.NONE:
            self._error(token=expr.keyword,
                        message='Cannot use \'super\' outside of a class.',
                        error_file=self._error_file)
        elif self._current_class != _ClassType.SUPERCLASS:
            self._error(token=expr.keyword,
                        message='Cannot use \'super\' in a class with no superclass.',
                        error_file=self._error_file)
        else:
            self._resolve_local(expr=expr, token=expr.keyword)

    def visit_unary_expr(self, expr: ExprUnary):
        self._resolve_expression(expr=expr.right)

    def visit_variable_expr(self, expr: ExprVariable):
        if self._scopes:
            scope = self._scopes.peek()
            if expr.name.lexeme in scope and not scope[expr.name.lexeme]:
                self._error(token=expr.name,
                            message='Cannot read local variable in its own initializer.',
                            error_file=self._error_file)

        self._resolve_local(expr=expr, token=expr.name)

    @staticmethod
    def _error(token, message, error_file):
        from . import PyLox
        PyLox.parse_error(token=token, message=message, error_file=error_file)
        return PyLoxRuntimeError(token=token, message=message)


class _Scopes(list):
    def __init__(self):
        super(_Scopes, self).__init__()

    def peek(self):
        return self[len(self) - 1]
