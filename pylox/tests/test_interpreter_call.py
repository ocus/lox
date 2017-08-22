import os

from tests import InterpreterTestHelper


def test_bool():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'call', 'bool.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Can only call functions and classes.' in err[0]
    assert '[line 1]' in err[1]


def test_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'call', 'nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Can only call functions and classes.' in err[0]
    assert '[line 1]' in err[1]


def test_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'call', 'num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Can only call functions and classes.' in err[0]
    assert '[line 1]' in err[1]


def test_object():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'call', 'object.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Can only call functions and classes.' in err[0]
    assert '[line 4]' in err[1]


def test_string():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'call', 'string.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Can only call functions and classes.' in err[0]
    assert '[line 1]' in err[1]
