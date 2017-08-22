import os

from tests import InterpreterTestHelper


def disabled_test_binary_trees():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'binary_trees.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def disabled_test_equality():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'equality.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def disabled_test_fib():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'fib.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def disabled_test_invocation():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'invocation.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def disabled_test_method_call():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'method_call.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def disabled_test_properties():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'properties.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def disabled_test_string_equality():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'benchmark', 'string_equality.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err
