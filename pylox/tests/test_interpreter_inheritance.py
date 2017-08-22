import os

from tests import InterpreterTestHelper


def test_inherit_from_function():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'inheritance', 'inherit_from_function.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Superclass must be a class.' in err[0]
    assert '[line 3]' in err[1]


def test_inherit_from_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'inheritance', 'inherit_from_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Superclass must be a class.' in err[0]
    assert '[line 2]' in err[1]


def test_inherit_from_number():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'inheritance', 'inherit_from_number.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Superclass must be a class.' in err[0]
    assert '[line 2]' in err[1]


def test_inherit_methods():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'inheritance', 'inherit_methods.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert 'foo' in out[0]
    assert 'bar' in out[1]
    assert 'bar' in out[2]
    assert [''] == err


def test_parenthesized_superclass():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'inheritance', 'parenthesized_superclass.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'(\': Expect superclass name.' in err[0]


def test_set_fields_from_base_class():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'inheritance', 'set_fields_from_base_class.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 6 == len(out)
    assert 'foo 1' in out[0]
    assert 'foo 2' in out[1]
    assert 'bar 1' in out[2]
    assert 'bar 2' in out[3]
    assert 'bar 1' in out[4]
    assert 'bar 2' in out[5]
    assert [''] == err
