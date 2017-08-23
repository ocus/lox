from typing import List

from .expr import AbstractExpr, ExprVariable, ExprUnary, ExprSuper, ExprThis, ExprSet, ExprLogical, ExprLiteral, \
    ExprGrouping, ExprGet, ExprCall, ExprBinary, ExprAssign
from .stmt import AbstractStmt, StmtWhile, StmtReturn, StmtVar, StmtPrint, StmtIf, StmtFunction, StmtExpression, \
    StmtClass, StmtBlock
from .token import Token


class Dumper(AbstractStmt.Visitor, AbstractExpr.Visitor):
    def dump_statements(self, statements: List[AbstractStmt]):
        return [self.dump_statement(statement=statement) for statement in statements]

    def dump_statement(self, statement: AbstractStmt):
        return statement.accept(self)

    def dump_expressions(self, expressions: List[AbstractExpr]):
        return [self.dump_expression(expression=expression) for expression in expressions]

    def dump_expression(self, expression: AbstractExpr):
        return expression.accept(self)

    def dump_tokens(self, tokens: List[Token]):
        return [self.dump_token(token=token) for token in tokens]

    def dump_token(self, token: Token):
        return token.lexeme

    def visit_block_stmt(self, stmt: StmtBlock):
        return {
            'block': self.dump_statements(statements=stmt.statements)
        }

    def visit_class_stmt(self, stmt: StmtClass):
        return {
            'class': {
                'name': self.dump_token(token=stmt.name),
                'superclass': self.dump_expression(expression=stmt.superclass) if stmt.superclass else None,
                'methods': self.dump_statements(statements=stmt.methods)
            }
        }

    def visit_expression_stmt(self, stmt: StmtExpression):
        return {
            'expression': self.dump_expression(expression=stmt.expression)
        }

    def visit_function_stmt(self, stmt: StmtFunction):
        return {
            'function': {
                'name': self.dump_token(token=stmt.name),
                'parameters': self.dump_tokens(tokens=stmt.parameters),
                'body': self.dump_statements(statements=stmt.body)
            }
        }

    def visit_if_stmt(self, stmt: StmtIf):
        return {
            'if': {
                'condition': self.dump_expression(expression=stmt.condition),
                'then': self.dump_statement(statement=stmt.then_branch),
                'else': self.dump_statement(statement=stmt.else_branch) if stmt.else_branch else None,
            }
        }

    def visit_print_stmt(self, stmt: StmtPrint):
        return {
            'print': self.dump_expression(expression=stmt.expression)
        }

    def visit_var_stmt(self, stmt: StmtVar):
        return {
            'var': {
                'name': self.dump_token(token=stmt.name),
                'initializer': self.dump_expression(expression=stmt.initializer) if stmt.initializer else None
            }
        }

    def visit_return_stmt(self, stmt: StmtReturn):
        return {
            'return': self.dump_expression(expression=stmt.value)
        }

    def visit_while_stmt(self, stmt: StmtWhile):
        return {
            'while': {
                'condition': self.dump_expression(expression=stmt.condition),
                'body': self.dump_statement(statement=stmt.body)
            }
        }

    def visit_assign_expr(self, expr: ExprAssign):
        return {
            'assign': {
                'name': self.dump_token(token=expr.name),
                'value': self.dump_expression(expression=expr.value)
            }
        }

    def visit_binary_expr(self, expr: ExprBinary):
        return {
            'binary': {
                'left': self.dump_expression(expression=expr.left),
                'operator': self.dump_token(token=expr.operator),
                'right': self.dump_expression(expression=expr.right),
            }
        }

    def visit_call_expr(self, expr: ExprCall):
        return {
            'call': {
                'callee': self.dump_expression(expression=expr.callee),
                'arguments': self.dump_expressions(expressions=expr.arguments),
            }
        }

    def visit_get_expr(self, expr: ExprGet):
        return {
            'get': {
                'object': self.dump_expression(expression=expr.object),
                'name': self.dump_token(token=expr.name),
            }
        }

    def visit_grouping_expr(self, expr: ExprGrouping):
        return {
            'grouping': self.dump_expression(expression=expr.expression),
        }

    def visit_literal_expr(self, expr: ExprLiteral):
        return expr.value

    def visit_logical_expr(self, expr: ExprLogical):
        return {
            'logical': {
                'left': self.dump_expression(expression=expr.left),
                'operator': self.dump_token(token=expr.operator),
                'right': self.dump_expression(expression=expr.right),
            }
        }

    def visit_set_expr(self, expr: ExprSet):
        return {
            'get': {
                'object': self.dump_expression(expression=expr.object),
                'name': self.dump_token(token=expr.name),
                'value': self.dump_expression(expression=expr.value),
            }
        }

    def visit_this_expr(self, expr: ExprThis):
        return self.dump_token(token=expr.keyword)

    def visit_super_expr(self, expr: ExprSuper):
        return {
            'super': {
                'method': self.dump_token(token=expr.keyword)
            }
        }

    def visit_unary_expr(self, expr: ExprUnary):
        return {
            'logical': {
                'operator': self.dump_token(token=expr.operator),
                'right': self.dump_expression(expression=expr.right),
            }
        }

    def visit_variable_expr(self, expr: ExprVariable):
        # return self._look_up_variable(token=expr.name, expr=expr)
        return {
            'variable': self.dump_token(token=expr.name)
        }
