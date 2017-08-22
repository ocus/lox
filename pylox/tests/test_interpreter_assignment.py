import os

from tests import InterpreterTestHelper


def test_associativity():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'associativity.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert 'c' in out[0]
    assert 'c' in out[1]
    assert 'c' in out[2]
    assert [''] == err


def test_global():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'global.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 4 == len(out)
    assert 'before' in out[0]
    assert 'after' in out[1]
    assert 'arg' in out[2]
    assert 'arg' in out[3]
    assert [''] == err


def test_grouping():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'grouping.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'=\': Invalid assignment target.' in err[0]


def test_infix_operator():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'infix_operator.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'=\': Invalid assignment target.' in err[0]


def test_local():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'local.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 4 == len(out)
    assert 'before' in out[0]
    assert 'after' in out[1]
    assert 'arg' in out[2]
    assert 'arg' in out[3]
    assert [''] == err


def test_prefix_operator():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'prefix_operator.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'=\': Invalid assignment target.' in err[0]


def test_syntax():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'syntax.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'var' in out[0]
    assert 'var' in out[1]
    assert [''] == err


def test_to_this():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'to_this.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'=\': Invalid assignment target.' in err[0]


def test_undefined():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'assignment', 'undefined.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined variable \'unknown\'.' in err[0]
    assert '[line 1]' in err[1]
