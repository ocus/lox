import os

from tests import InterpreterTestHelper


def test_class_in_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'class_in_body.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'class\': Expect expression.' in err[0]


def test_closure_in_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'closure_in_body.lox'
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


def test_fun_in_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'fun_in_body.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'fun\': Expect expression.' in err[0]


def test_return_closure():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'return_closure.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'i' in out[0]
    assert [''] == err


def test_return_inside():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'return_inside.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'i' in out[0]
    assert [''] == err


def test_syntax():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'syntax.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 6 == len(out)
    assert '1' in out[0]
    assert '2' in out[1]
    assert '3' in out[2]
    assert '0' in out[3]
    assert '1' in out[4]
    assert '2' in out[5]
    assert [''] == err


def test_var_in_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'while', 'var_in_body.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'var\': Expect expression.' in err[0]
