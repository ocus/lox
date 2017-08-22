import os

from tests import InterpreterTestHelper


def test_concatenation():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'string', 'concatenation.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 6 == len(out)
    assert 'ab' in out[0]
    assert 'c1.23' in out[1]
    assert '4.56d' in out[2]
    assert 'e4.123456789012345' in out[3]
    assert '2112' in out[4]
    assert '202030' in out[5]
    assert [''] == err


def test_error_after_multiline():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'string', 'error_after_multiline.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined variable \'err\'.' in err[0]
    assert '[line 7]' in err[1]


def test_literals():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'string', 'literals.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert '()' in out[0]
    assert 'a string' in out[1]
    assert 'A~¶Þॐஃ' in out[2]
    assert [''] == err


def test_multiline():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'string', 'multiline.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert '1' in out[0]
    assert '2' in out[1]
    assert '3' in out[2]
    assert [''] == err


def test_unterminated():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'string', 'unterminated.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error: Unterminated string.' in err[0]
