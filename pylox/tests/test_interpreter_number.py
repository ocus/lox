import os

from tests import InterpreterTestHelper


def test_decimal_point_at_eof():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'number', 'decimal_point_at_eof.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at end: Expect property name after \'.\'.' in err[0]


def test_leading_dot():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'number', 'leading_dot.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'.\': Expect expression.' in err[0]


def test_literals():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'number', 'literals.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 6 == len(out)
    assert '123' in out[0]
    assert '987654' in out[1]
    assert '0' in out[2]
    assert '-0' in out[3]
    assert '123.456' in out[4]
    assert '-0.001' in out[5]
    assert [''] == err


def test_trailing_dot():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'number', 'trailing_dot.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \';\': Expect property name after \'.\'.' in err[0]
