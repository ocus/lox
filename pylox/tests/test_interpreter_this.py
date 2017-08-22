import os

from tests import InterpreterTestHelper


def test_closure():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'this', 'closure.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'Foo' in out[0]
    assert [''] == err


def test_nested_class():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'this', 'nested_class.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert 'Outer instance' in out[0]
    assert 'Outer instance' in out[1]
    assert 'Inner instance' in out[2]
    assert [''] == err


def test_nested_closure():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'this', 'nested_closure.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'Foo' in out[0]
    assert [''] == err


def test_this_at_top_level():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'this', 'this_at_top_level.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'this\': Cannot use \'this\' outside of a class.' in err[0]


def test_this_in_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'this', 'this_in_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'baz' in out[0]
    assert [''] == err


def test_this_in_top_level_function():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'this', 'this_in_top_level_function.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'this\': Cannot use \'this\' outside of a class.' in err[0]
