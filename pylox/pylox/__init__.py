import sys

from .error import PyLoxRuntimeError
from .interface import InterpreterInterface
from .interpreter import Interpreter, debugger
from .parser import Parser
from .resolver import Resolver
from .scanner import Scanner
from .token import Token, TokenType

__version__ = "0.0.2"


class PyLox(object):
    _has_error = False
    _has_runtime_error = False

    @staticmethod
    def run(interpreter: InterpreterInterface, code: str, error_file=sys.stderr, debug=False):
        try:
            if debug:
                interpreter = debugger(interpreter=interpreter)
            PyLox._has_error = False
            pylox_scanner = Scanner(source=code, error_file=error_file)
            tokens = pylox_scanner.scan_tokens()

            pylox_parser = Parser(tokens=tokens, error_file=error_file)
            statements = pylox_parser.parse()

            if PyLox._has_error:
                return

            resolver = Resolver(interpreter=interpreter, error_file=error_file)
            resolver.resolve(statements=statements)

            if PyLox._has_error:
                return

            interpreter.interpret(statements=statements)
        except Exception as e:
            print(e, file=sys.stderr)

    @staticmethod
    def run_file(file_path, debug=False):
        with open(file=file_path, mode='r') as f:
            PyLox.run(interpreter=Interpreter(), code=f.read(), debug=debug)

    @staticmethod
    def run_prompt(debug=False):
        print('PyLox', __version__)
        interpreter = Interpreter()
        while True:
            line = input('$ ')
            if not line:
                break
            PyLox.run(interpreter=interpreter, code=line, debug=debug)

    @staticmethod
    def error(line, message, error_file=sys.stderr):
        PyLox._report(line=line, where="", message=message, error_file=error_file)

    @staticmethod
    def parse_error(token, message, error_file=sys.stderr):
        PyLox._report(line=token.line,
                      where=' at end' if token.type == TokenType.EOF else ' at \'' + token.lexeme + '\'',
                      message=message,
                      error_file=error_file)

    @staticmethod
    def _report(line, where, message, error_file=sys.stderr):
        print('[line {}] Error{}: {}'.format(line, where, message), file=error_file)
        PyLox._has_error = True

    @staticmethod
    def runtime_error(error: PyLoxRuntimeError, error_file=sys.stderr):
        print(error.message + '\n[line ' + str(error.token.line) + ']', file=error_file)
        PyLox._has_runtime_error = True
