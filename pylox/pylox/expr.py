from abc import ABC, abstractmethod
from typing import Union, List

from .token import Token

PyLoxLiteral = Union[float, str, None]


class AbstractExpr(ABC):
    @abstractmethod
    def accept(self, visitor: 'AbstractExpr.Visitor'):
        pass

    class Visitor(ABC):
        @abstractmethod
        def visit_assign_expr(self, expr: 'ExprAssign'):
            pass

        @abstractmethod
        def visit_binary_expr(self, expr: 'ExprBinary'):
            pass

        @abstractmethod
        def visit_call_expr(self, expr: 'ExprCall'):
            pass

        @abstractmethod
        def visit_get_expr(self, expr: 'ExprGet'):
            pass

        @abstractmethod
        def visit_grouping_expr(self, expr: 'ExprGrouping'):
            pass

        @abstractmethod
        def visit_literal_expr(self, expr: 'ExprLiteral'):
            pass

        @abstractmethod
        def visit_logical_expr(self, expr: 'ExprLogical'):
            pass

        @abstractmethod
        def visit_set_expr(self, expr: 'ExprSet'):
            pass

        @abstractmethod
        def visit_this_expr(self, expr: 'ExprThis'):
            pass

        @abstractmethod
        def visit_super_expr(self, expr: 'ExprSuper'):
            pass

        @abstractmethod
        def visit_unary_expr(self, expr: 'ExprUnary'):
            pass

        @abstractmethod
        def visit_variable_expr(self, expr: 'ExprVariable'):
            pass


class ExprAssign(AbstractExpr):
    def __init__(self, name: 'Token', value: 'AbstractExpr'):
        self.name = name
        self.value = value

    def accept(self, visitor):
        return visitor.visit_assign_expr(expr=self)


class ExprBinary(AbstractExpr):
    def __init__(self, left: 'AbstractExpr', operator: 'Token', right: 'AbstractExpr'):
        self.left = left
        self.operator = operator
        self.right = right

    def accept(self, visitor):
        return visitor.visit_binary_expr(expr=self)


class ExprCall(AbstractExpr):
    def __init__(self, callee: 'AbstractExpr', paren: 'Token', arguments: 'List[AbstractExpr]'):
        self.callee = callee
        self.paren = paren
        self.arguments = arguments

    def accept(self, visitor):
        return visitor.visit_call_expr(expr=self)


class ExprGet(AbstractExpr):
    def __init__(self, object: 'AbstractExpr', name: 'Token'):
        self.object = object
        self.name = name

    def accept(self, visitor):
        return visitor.visit_get_expr(expr=self)


class ExprGrouping(AbstractExpr):
    def __init__(self, expression: 'AbstractExpr'):
        self.expression = expression

    def accept(self, visitor):
        return visitor.visit_grouping_expr(expr=self)


class ExprLiteral(AbstractExpr):
    def __init__(self, value: 'PyLoxLiteral'):
        self.value = value

    def accept(self, visitor):
        return visitor.visit_literal_expr(expr=self)


class ExprLogical(AbstractExpr):
    def __init__(self, left: 'AbstractExpr', operator: 'Token', right: 'AbstractExpr'):
        self.left = left
        self.operator = operator
        self.right = right

    def accept(self, visitor):
        return visitor.visit_logical_expr(expr=self)


class ExprSet(AbstractExpr):
    def __init__(self, object: 'AbstractExpr', name: 'Token', value: 'AbstractExpr'):
        self.object = object
        self.name = name
        self.value = value

    def accept(self, visitor):
        return visitor.visit_set_expr(expr=self)


class ExprThis(AbstractExpr):
    def __init__(self, keyword: 'Token'):
        self.keyword = keyword

    def accept(self, visitor):
        return visitor.visit_this_expr(expr=self)


class ExprSuper(AbstractExpr):
    def __init__(self, keyword: 'Token', method: 'Token'):
        self.keyword = keyword
        self.method = method

    def accept(self, visitor):
        return visitor.visit_super_expr(expr=self)


class ExprUnary(AbstractExpr):
    def __init__(self, operator: 'Token', right: 'AbstractExpr'):
        self.operator = operator
        self.right = right

    def accept(self, visitor):
        return visitor.visit_unary_expr(expr=self)


class ExprVariable(AbstractExpr):
    def __init__(self, name: 'Token'):
        self.name = name

    def accept(self, visitor):
        return visitor.visit_variable_expr(expr=self)

