import os

from tests import InterpreterTestHelper


def test_body_must_be_block():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'body_must_be_block.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'123\': Expect \'{\' before function body.' in err[0]


def test_empty_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'empty_body.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'extra_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 4.' in err[0]
    assert '[line 6]' in err[1]


def test_local_mutual_recursion():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'local_mutual_recursion.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined variable \'isOdd\'.' in err[0]
    assert '[line 4]' in err[1]


def test_local_recursion():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'local_recursion.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '21' in out[0]
    assert [''] == err


def test_missing_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'missing_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 1.' in err[0]
    assert '[line 3]' in err[1]


def test_missing_comma_in_parameters():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'missing_comma_in_parameters.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'c\': Expect \')\' after parameters.' in err[0]


def test_mutual_recursion():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'mutual_recursion.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'true' in out[0]
    assert 'true' in out[1]
    assert [''] == err


def test_parameters():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'parameters.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 9 == len(out)
    assert '0' in out[0]
    assert '1' in out[1]
    assert '3' in out[2]
    assert '6' in out[3]
    assert '10' in out[4]
    assert '15' in out[5]
    assert '21' in out[6]
    assert '28' in out[7]
    assert '36' in out[8]
    assert [''] == err


def test_print():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'print.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '<fn foo>' in out[0]
    assert [''] == err


def test_recursion():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'recursion.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '21' in out[0]
    assert [''] == err


def test_too_many_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'too_many_arguments.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'function', 'too_many_parameters.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'i\': Cannot have more than 8 parameters.' in err[0]
