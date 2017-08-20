from abc import ABC, abstractmethod
from typing import Any, Dict, List

from .environment import Environment
from .error import PyLoxRuntimeError
from .interface import InterpreterInterface
from .stmt import StmtFunction
from .token import Token


class Return(RuntimeError):
    def __init__(self, value):
        super(Return, self).__init__()
        self.value = value


class LoxCallable(ABC):
    @abstractmethod
    def arity(self) -> int:
        pass

    @abstractmethod
    def call(self, interpreter: InterpreterInterface, arguments: List[Any]):
        pass


class LoxFunction(LoxCallable):
    def __init__(self, declaration: StmtFunction, closure: Environment, is_initializer: bool = False):
        self._declaration = declaration
        self._closure = closure
        self._is_initializer = is_initializer

    def arity(self) -> int:
        return len(self._declaration.parameters)

    def call(self, interpreter: InterpreterInterface, arguments: List[Any]):
        environment = Environment(enclosing=self._closure)
        for i, parameter in enumerate(self._declaration.parameters):
            environment.define(name=parameter.lexeme, value=arguments[i])

        try:
            interpreter.execute_block(statements=self._declaration.body, environment=environment)
        except Return as return_value:
            return return_value.value

        if self._is_initializer:
            return self._closure.get_at(distance=0, name='this')

        return None

    def bind(self, instance: 'LoxInstance'):
        environment = Environment(enclosing=self._closure)
        environment.define(name='this', value=instance)
        return LoxFunction(declaration=self._declaration, closure=environment, is_initializer=self._is_initializer)

    def __str__(self):
        return '<fn ' + self._declaration.name.lexeme + '>'


class LoxClass(LoxCallable):
    def __init__(self, name: str, superclass: 'LoxClass', methods: Dict[str, LoxFunction]):
        self._name = name
        self._superclass = superclass
        self._methods = methods

    def arity(self) -> int:
        if 'init' in self._methods:
            return self._methods['init'].arity()
        return 0

    def call(self, interpreter: InterpreterInterface, arguments: List[Any]):
        instance = LoxInstance(cls=self)
        if 'init' in self._methods:
            self._methods['init'].bind(instance=instance).call(interpreter=interpreter, arguments=arguments)
        return instance

    def find_method(self, instance: 'LoxInstance', name: str):
        cls = self
        while cls is not None:
            if name in cls._methods:
                return cls._methods[name].bind(instance=instance)
            cls = cls._superclass
        return None

    def __str__(self):
        return self._name


class LoxInstance(object):
    def __init__(self, cls: LoxClass):
        self._cls = cls
        self.fields = {}  # type: Dict[str, Any]

    def get_property(self, token: Token):
        if token.lexeme in self.fields:
            return self.fields[token.lexeme]

        method = self._cls.find_method(instance=self, name=token.lexeme)
        if method:
            return method

        raise PyLoxRuntimeError(token=token, message="Undefined property '" + token.lexeme + "'.")

    def __str__(self):
        return str(self._cls) + ' instance'

