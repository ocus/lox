import os

from tests import InterpreterTestHelper


def test_class_in_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'class_in_body.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'closure_in_body.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'fun_in_body.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'return_closure.lox'
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
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'return_inside.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'i' in out[0]
    assert [''] == err


def test_scope():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'scope.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 4 == len(out)
    assert '0' in out[0]
    assert '-1' in out[1]
    assert 'after' in out[2]
    assert '0' in out[3]
    assert [''] == err


def test_statement_condition():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'statement_condition.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Error at \'{\': Expect expression.' in err[0]
    assert 'Error at \')\': Expect \';\' after expression.' in err[1]


def test_statement_increment():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'statement_increment.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'{\': Expect expression.' in err[0]


def test_statement_initializer():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'statement_initializer.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Error at \'{\': Expect expression.' in err[0]
    assert 'Error at \')\': Expect \';\' after expression.' in err[1]


def test_syntax():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'syntax.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 14 == len(out)
    assert '1' in out[0]
    assert '2' in out[1]
    assert '3' in out[2]
    assert '0' in out[3]
    assert '1' in out[4]
    assert '2' in out[5]
    assert 'done' in out[6]
    assert '0' in out[7]
    assert '1' in out[8]
    assert '0' in out[9]
    assert '1' in out[10]
    assert '2' in out[11]
    assert '0' in out[12]
    assert '1' in out[13]
    assert [''] == err


def test_var_in_body():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'for', 'var_in_body.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'var\': Expect expression.' in err[0]
