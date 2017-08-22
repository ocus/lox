import os

from tests import InterpreterTestHelper


def test_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert 'init' in out[0]
    assert '1' in out[1]
    assert '2' in out[2]
    assert [''] == err


def test_call_init_explicitly():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'call_init_explicitly.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 4 == len(out)
    assert 'Foo.init(one)' in out[0]
    assert 'Foo.init(two)' in out[1]
    assert 'Foo instance' in out[2]
    assert 'init' in out[3]
    assert [''] == err


def test_default():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'default.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'Foo instance' in out[0]
    assert [''] == err


def test_default_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'default_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 0 arguments but got 3.' in err[0]
    assert '[line 3]' in err[1]


def test_early_return():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'early_return.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'init' in out[0]
    assert 'Foo instance' in out[1]
    assert [''] == err


def test_extra_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'extra_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 4.' in err[0]
    assert '[line 8]' in err[1]


def test_missing_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'missing_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 1.' in err[0]
    assert '[line 5]' in err[1]


def test_return_in_nested_function():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'return_in_nested_function.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'bar' in out[0]
    assert 'Foo instance' in out[1]
    assert [''] == err


def test_return_value():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'constructor', 'return_value.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'return\': Cannot return value from an initializer.' in err[0]
