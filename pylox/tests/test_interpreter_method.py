import os

from tests import InterpreterTestHelper


def test_arity():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'arity.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 9 == len(out)
    assert 'no args' in out[0]
    assert '1' in out[1]
    assert '3' in out[2]
    assert '6' in out[3]
    assert '10' in out[4]
    assert '15' in out[5]
    assert '21' in out[6]
    assert '28' in out[7]
    assert '36' in out[8]
    assert [''] == err


def test_empty_block():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'empty_block.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'nil' in out[0]
    assert [''] == err


def test_extra_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'extra_arguments.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'missing_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 1.' in err[0]
    assert '[line 5]' in err[1]


def test_not_found():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'not_found.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined property \'unknown\'.' in err[0]
    assert '[line 3]' in err[1]


def test_refer_to_name():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'refer_to_name.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined variable \'method\'.' in err[0]
    assert '[line 3]' in err[1]


def test_too_many_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'too_many_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'9\': Cannot have more than 8 arguments.' in err[0]


def test_too_many_parameters():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'method', 'too_many_parameters.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'i\': Cannot have more than 8 parameters.' in err[0]
