from .error import PyLoxRuntimeError
from .interface import EnvironmentInterface
from .token import Token


class Environment(EnvironmentInterface):
    def __init__(self, enclosing: 'Environment' = None):
        self.enclosing = enclosing
        self._values = {}

    def define(self, name: str, value):
        self._values[name] = value

    def assign(self, token: Token, value):
        if token.lexeme in self._values:
            self._values[token.lexeme] = value
            return

        if self.enclosing:
            self.enclosing.assign(token, value)
            return

        raise PyLoxRuntimeError(token=token, message="Undefined variable '" + token.lexeme + "'.")

    def get(self, token: Token):
        if token.lexeme in self._values:
            return self._values[token.lexeme]

        if self.enclosing:
            return self.enclosing.get(token=token)

        raise PyLoxRuntimeError(token=token, message="Undefined variable '" + token.lexeme + "'.")

    def assign_at(self, distance: int, token: Token, value):
        environment = self
        for i in range(0, distance):
            environment = environment.enclosing

        environment._values[token.lexeme] = value

    def get_at(self, distance: int, name: str):
        environment = self
        for i in range(0, distance):
            environment = environment.enclosing

        return environment._values[name]

    def __str__(self):
        result = str(self._values)
        if self.enclosing:
            result += ' -> ' + str(self.enclosing)
        return result
