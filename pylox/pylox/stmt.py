from abc import ABC, abstractmethod
from typing import List

from .expr import AbstractExpr
from .token import Token


class AbstractStmt(ABC):
    @abstractmethod
    def accept(self, visitor: 'AbstractStmt.Visitor'):
        pass

    class Visitor(ABC):
        @abstractmethod
        def visit_block_stmt(self, stmt: 'StmtBlock'):
            pass

        @abstractmethod
        def visit_class_stmt(self, stmt: 'StmtClass'):
            pass

        @abstractmethod
        def visit_expression_stmt(self, stmt: 'StmtExpression'):
            pass

        @abstractmethod
        def visit_function_stmt(self, stmt: 'StmtFunction'):
            pass

        @abstractmethod
        def visit_if_stmt(self, stmt: 'StmtIf'):
            pass

        @abstractmethod
        def visit_print_stmt(self, stmt: 'StmtPrint'):
            pass

        @abstractmethod
        def visit_var_stmt(self, stmt: 'StmtVar'):
            pass

        @abstractmethod
        def visit_return_stmt(self, stmt: 'StmtReturn'):
            pass

        @abstractmethod
        def visit_while_stmt(self, stmt: 'StmtWhile'):
            pass


class StmtBlock(AbstractStmt):
    def __init__(self, statements: 'List[AbstractStmt]'):
        self.statements = statements

    def accept(self, visitor):
        return visitor.visit_block_stmt(stmt=self)


class StmtClass(AbstractStmt):
    def __init__(self, name: 'Token', superclass: 'AbstractExpr', methods: 'List[StmtFunction]'):
        self.name = name
        self.superclass = superclass
        self.methods = methods

    def accept(self, visitor):
        return visitor.visit_class_stmt(stmt=self)


class StmtExpression(AbstractStmt):
    def __init__(self, expression: 'AbstractExpr'):
        self.expression = expression

    def accept(self, visitor):
        return visitor.visit_expression_stmt(stmt=self)


class StmtFunction(AbstractStmt):
    def __init__(self, name: 'Token', parameters: 'List[Token]', body: 'List[AbstractStmt]'):
        self.name = name
        self.parameters = parameters
        self.body = body

    def accept(self, visitor):
        return visitor.visit_function_stmt(stmt=self)


class StmtIf(AbstractStmt):
    def __init__(self, condition: 'AbstractExpr', then_branch: 'AbstractStmt', else_branch: 'AbstractStmt'):
        self.condition = condition
        self.then_branch = then_branch
        self.else_branch = else_branch

    def accept(self, visitor):
        return visitor.visit_if_stmt(stmt=self)


class StmtPrint(AbstractStmt):
    def __init__(self, expression: 'AbstractExpr'):
        self.expression = expression

    def accept(self, visitor):
        return visitor.visit_print_stmt(stmt=self)


class StmtVar(AbstractStmt):
    def __init__(self, name: 'Token', initializer: 'AbstractExpr'):
        self.name = name
        self.initializer = initializer

    def accept(self, visitor):
        return visitor.visit_var_stmt(stmt=self)


class StmtReturn(AbstractStmt):
    def __init__(self, keyword: 'Token', value: 'AbstractExpr'):
        self.keyword = keyword
        self.value = value

    def accept(self, visitor):
        return visitor.visit_return_stmt(stmt=self)


class StmtWhile(AbstractStmt):
    def __init__(self, condition: 'AbstractExpr', body: 'AbstractStmt'):
        self.condition = condition
        self.body = body

    def accept(self, visitor):
        return visitor.visit_while_stmt(stmt=self)

