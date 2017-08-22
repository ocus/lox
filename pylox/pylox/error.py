from .token import Token


class PyLoxRuntimeError(Exception):
    def __init__(self, token: Token, message: str):
        super(PyLoxRuntimeError, self).__init__(message)
        self.token = token
        self.message = message

    def __str__(self):
        return 'PyLoxRuntimeError at \'' + self.token.lexeme + '\' [line ' + str(self.token.line) + ']: ' + self.message


class PyLoxNotImplementedError(NotImplementedError):
    pass
