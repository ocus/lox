import sys
import time
from typing import Any, Dict, List

from .callable import LoxCallable, LoxClass, LoxFunction, LoxInstance, Return
from .environment import Environment
from .error import PyLoxRuntimeError
from .expr import AbstractExpr, ExprAssign, ExprBinary, ExprCall, ExprGet, ExprGrouping, ExprLiteral, ExprLogical, \
    ExprSet, ExprSuper, ExprThis, ExprUnary, ExprVariable
from .interface import EnvironmentInterface, InterpreterInterface
from .stmt import AbstractStmt, StmtBlock, StmtClass, StmtExpression, StmtFunction, StmtIf, StmtPrint, StmtReturn, \
    StmtVar, StmtWhile
from .token import Token, TokenType


class StdClock(LoxCallable):
    def call(self, interpreter: InterpreterInterface, arguments: List[Any]):
        return time.time()

    def arity(self) -> int:
        return 0

    def __str__(self):
        return '<fn:lox clock>'


class StdGetEnv(LoxCallable):
    def __init__(self):
        super(StdGetEnv, self).__init__()

    def call(self, interpreter: InterpreterInterface, arguments: List[Any]):
        return interpreter.get_env()

    def arity(self) -> int:
        return 0

    def __str__(self):
        return '<fn:lox get_env>'


class Interpreter(InterpreterInterface, AbstractStmt.Visitor, AbstractExpr.Visitor):
    def __init__(self, out_file=sys.stdout, error_file=sys.stderr):
        self._out_file = out_file
        self._error_file = error_file
        self.globals = Environment()
        self._environment = self.globals
        self._locals = {}  # type: Dict[AbstractExpr, int]

        self.globals.define('clock', StdClock())
        self.globals.define('get_env', StdGetEnv())

    def get_env(self):
        return self._environment

    def interpret(self, statements: List[AbstractStmt]):
        try:
            for statement in statements:
                self._execute(statement=statement)
        except PyLoxRuntimeError as error:
            from . import PyLox
            PyLox.runtime_error(error=error, error_file=self._error_file)

    def execute_block(self, statements: List[AbstractStmt], environment: EnvironmentInterface):
        previous = self._environment
        try:
            self._environment = environment

            for statement in statements:
                self._execute(statement=statement)
        finally:
            self._environment = previous

    def resolve(self, expr: AbstractExpr, depth: int):
        self._locals[expr] = depth

    def _execute(self, statement: AbstractStmt):
        statement.accept(visitor=self)

    def _evaluate(self, expr: AbstractExpr):
        return expr.accept(visitor=self)

    def _look_up_variable(self, token: Token, expr: AbstractExpr):
        distance = self._locals.get(expr)
        if distance is not None:
            return self._environment.get_at(distance=distance, name=token.lexeme)
        return self.globals.get(token=token)

    def visit_block_stmt(self, stmt: StmtBlock):
        self.execute_block(statements=stmt.statements, environment=Environment(enclosing=self._environment))
        return None

    def visit_class_stmt(self, stmt: StmtClass):
        self._environment.define(name=stmt.name.lexeme, value=None)

        superclass = None
        if stmt.superclass:
            superclass = self._evaluate(stmt.superclass)
            if not isinstance(superclass, LoxClass):
                raise PyLoxRuntimeError(stmt.name, 'Superclass must be a class.')

            self._environment = Environment(enclosing=self._environment)
            self._environment.define(name='super', value=superclass)

        methods = {}  # type: Dict[str, LoxFunction]
        for method in stmt.methods:
            function = LoxFunction(declaration=method,
                                   closure=self._environment,
                                   is_initializer=method.name.lexeme == 'init')
            methods[method.name.lexeme] = function

        cls = LoxClass(name=stmt.name.lexeme, superclass=superclass, methods=methods)
        if superclass:
            self._environment = self._environment.enclosing

        self._environment.assign(stmt.name, cls)
        return None

    def visit_expression_stmt(self, stmt: StmtExpression):
        return self._evaluate(expr=stmt.expression)

    def visit_function_stmt(self, stmt: StmtFunction):
        function = LoxFunction(declaration=stmt, closure=self._environment, is_initializer=False)
        self._environment.define(name=stmt.name.lexeme, value=function)
        return None

    def visit_if_stmt(self, stmt: StmtIf):
        if Interpreter._is_truthy(value=self._evaluate(expr=stmt.condition)):
            self._execute(statement=stmt.then_branch)
        elif stmt.else_branch:
            self._execute(statement=stmt.else_branch)

    def visit_print_stmt(self, stmt: StmtPrint):
        value = self._evaluate(expr=stmt.expression)
        print(self._stringify(obj=value), end='\n', file=self._out_file)

    def visit_var_stmt(self, stmt: StmtVar):
        value = None
        if stmt.initializer:
            value = self._evaluate(expr=stmt.initializer)

        self._environment.define(name=stmt.name.lexeme, value=value)
        return None

    def visit_return_stmt(self, stmt: StmtReturn):
        value = None
        if stmt.value:
            value = self._evaluate(expr=stmt.value)

        raise Return(value=value)

    def visit_while_stmt(self, stmt: StmtWhile):
        while self._is_truthy(value=self._evaluate(expr=stmt.condition)):
            self._execute(statement=stmt.body)
        return None

    def visit_assign_expr(self, expr: ExprAssign):
        value = self._evaluate(expr=expr.value)

        distance = self._locals[expr] if expr in self._locals else None
        if distance is not None:
            self._environment.assign_at(distance=distance, token=expr.name, value=value)
        else:
            self.globals.assign(token=expr.name, value=value)
        return value

    def visit_binary_expr(self, expr: ExprBinary):
        left = self._evaluate(expr=expr.left)
        right = self._evaluate(expr=expr.right)

        if TokenType.MINUS == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            return left - right
        if TokenType.PLUS == expr.operator.type:
            left_is_float = Interpreter._is_float(value=left)
            right_is_float = Interpreter._is_float(value=right)
            if left_is_float and right_is_float:
                return left + right
            left_is_str = isinstance(left, str)
            right_is_str = isinstance(right, str)
            if left_is_str and right_is_str:
                return left + right
            if left_is_float and right_is_str:
                return Interpreter._stringify(obj=left) + right
            if left_is_str and right_is_float:
                return left + Interpreter._stringify(obj=right)
            raise PyLoxRuntimeError(expr.operator, message='Operands must be two numbers or two strings.')
        if TokenType.SLASH == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            Interpreter._check_not_zero(operator=expr.operator, operand=right)
            return left / right
        if TokenType.STAR == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            return left * right
        if TokenType.GREATER == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            return left > right
        if TokenType.GREATER_EQUAL == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            return left >= right
        if TokenType.LESS == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            return left < right
        if TokenType.LESS_EQUAL == expr.operator.type:
            Interpreter._check_number_operands(operator=expr.operator, left=left, right=right)
            return left <= right
        if TokenType.BANG_EQUAL == expr.operator.type:
            return not Interpreter._is_equal(a=left, b=right)
        if TokenType.EQUAL_EQUAL == expr.operator.type:
            return Interpreter._is_equal(a=left, b=right)

        raise PyLoxRuntimeError(token=expr.operator, message='Unknown binary operator.')

    def visit_call_expr(self, expr: ExprCall):
        callee = self._evaluate(expr=expr.callee)

        arguments = []
        for argument in expr.arguments:
            arguments.append(self._evaluate(expr=argument))

        if not isinstance(callee, LoxCallable):
            raise PyLoxRuntimeError(token=expr.paren, message='Can only call functions and classes.')

        if len(arguments) != callee.arity():
            raise PyLoxRuntimeError(token=expr.paren,
                                    message='Expected ' + str(callee.arity()) + ' arguments but got ' +
                                            str(len(arguments)) + '.')
        return callee.call(interpreter=self, arguments=arguments)

    def visit_get_expr(self, expr: ExprGet):
        object = self._evaluate(expr=expr.object)  # type: LoxInstance
        if isinstance(object, LoxInstance):
            return object.get_property(token=expr.name)

        raise PyLoxRuntimeError(token=expr.name, message='Only instances have properties.')

    def visit_grouping_expr(self, expr: ExprGrouping):
        return self._evaluate(expr=expr.expression)

    def visit_literal_expr(self, expr: ExprLiteral):
        return expr.value

    def visit_logical_expr(self, expr: ExprLogical):
        left = self._evaluate(expr=expr.left)

        if TokenType.OR == expr.operator.type:
            if Interpreter._is_truthy(value=left):
                return left
        else:
            if not Interpreter._is_truthy(value=left):
                return left

        return self._evaluate(expr=expr.right)

    def visit_set_expr(self, expr: ExprSet):
        value = self._evaluate(expr=expr.value)
        object = self._evaluate(expr=expr.object)

        if isinstance(object, LoxInstance):
            instance = object  # type: LoxInstance
            instance.fields[expr.name.lexeme] = value
            return value

        raise PyLoxRuntimeError(token=expr.name, message='Only instances have fields.')

    def visit_this_expr(self, expr: ExprThis):
        return self._environment.get(token=expr.keyword)

    def visit_super_expr(self, expr: ExprSuper):
        distance = self._locals[expr]
        superclass = self._environment.get_at(distance=distance, name='super')  # type: LoxClass
        receiver = self._environment.get_at(distance=distance - 1, name='this')
        method = superclass.find_method(instance=receiver, name=expr.method.lexeme)
        if not method:
            raise PyLoxRuntimeError(token=expr.method, message='Undefined property \'' + expr.method.lexeme + '\'.')
        return method

    def visit_unary_expr(self, expr: ExprUnary):
        right = self._evaluate(expr=expr.right)

        if TokenType.BANG == expr.operator.type:
            return not Interpreter._is_truthy(value=right)
        if TokenType.MINUS == expr.operator.type:
            Interpreter._check_number_operand(operator=expr.operator, operand=right)
            return -right

        raise PyLoxRuntimeError(token=expr.operator, message='Unknown unary operator.')

    def visit_variable_expr(self, expr: ExprVariable):
        return self._look_up_variable(token=expr.name, expr=expr)

    @staticmethod
    def _is_truthy(value: Any):
        if value is None:
            return False
        if isinstance(value, bool):
            return value
        return True

    @staticmethod
    def _is_equal(a: Any, b: Any):
        return type(a) == type(b) and a == b

    @staticmethod
    def _is_float(value: Any):
        return isinstance(value, float)

    @staticmethod
    def _check_number_operand(operator: Token, operand: Any):
        if Interpreter._is_float(value=operand):
            return True
        raise PyLoxRuntimeError(token=operator, message='Operand must be a number.')

    @staticmethod
    def _check_number_operands(operator: Token, left: Any, right: Any):
        if Interpreter._is_float(value=left) and Interpreter._is_float(value=right):
            return True
        raise PyLoxRuntimeError(token=operator, message='Operands must be numbers.')

    @staticmethod
    def _check_not_zero(operator: Token, operand: Any):
        if operand != 0:
            return
        raise PyLoxRuntimeError(token=operator, message='Division by zero.')

    @staticmethod
    def _stringify(obj: Any):
        if obj is None:
            return 'nil'

        if isinstance(obj, bool):
            return 'true' if obj else 'false'

        # HACK remove the ending .0 if necessary
        if Interpreter._is_float(value=obj):
            text = str(obj)
            if text.endswith('.0'):
                text = text[:text.index('.0')]
            return text

        return str(obj)
