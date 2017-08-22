import io

from pylox import PyLox
from pylox.interpreter import Interpreter


class InterpreterTestHelper(object):
    def __init__(self, source_file):
        with open(file=source_file, mode='r', encoding='utf8') as f:
            self._source = f.read()
        self._out_file = io.StringIO(initial_value=None, newline=None)
        self._error_file = io.StringIO(initial_value=None, newline=None)

    def run(self):
        interpreter = Interpreter(out_file=self._out_file, error_file=self._error_file)
        PyLox.run(interpreter, code=self._source, error_file=self._error_file)

    def get_output(self):
        return self._to_lines(value=self._out_file.getvalue())

    def get_error(self):
        return self._to_lines(value=self._error_file.getvalue())

    def __del__(self):
        self._out_file.close()
        self._error_file.close()

    @staticmethod
    def _to_lines(value):
        if value and value[-1] == '\n':
            value = value[:-1]
        return value.split('\n')
