from .error import PyLoxRuntimeError
from .interface import InterpreterInterface
from .interpreter import Interpreter
from .parser import Parser
from .resolver import Resolver
from .scanner import Scanner
from .token import Token, TokenType

__version__ = "0.0.1"


class PyLox(object):
    _has_error = False

    @staticmethod
    def _run(interpreter: InterpreterInterface, code: str):
        PyLox._has_error = False
        pylox_scanner = Scanner(source=code)
        tokens = pylox_scanner.scan_tokens()

        pylox_parser = Parser(tokens)
        statements = pylox_parser.parse()

        if PyLox._has_error:
            return

        resolver = Resolver(interpreter=interpreter)
        resolver.resolve(statements=statements)

        if PyLox._has_error:
            return

        interpreter.interpret(statements=statements)

    @staticmethod
    def run_file(file_path):
        with open(file=file_path, mode='r') as f:
            try:
                PyLox._run(interpreter=Interpreter(), code=f.read())
            except (PyLoxRuntimeError, Exception) as e:
                print(e)

    @staticmethod
    def run_prompt():
        print('PyLox', __version__)
        interpreter = Interpreter()
        while True:
            line = input('$ ')
            if not line:
                break
            try:
                PyLox._run(interpreter=interpreter, code=line)
            except (PyLoxRuntimeError, Exception) as e:
                print(e)

    @staticmethod
    def error(line, message):
        PyLox._report(line, "", message)

    @staticmethod
    def parse_error(token, message):
        PyLox._report(token.line, ' at end' if token.type == TokenType.EOF else ' at \'' + token.lexeme + '\'', message)

    @staticmethod
    def _report(line, where, message):
        print('[line {}] Error{}: {}'.format(line, where, message))
        PyLox._has_error = True
