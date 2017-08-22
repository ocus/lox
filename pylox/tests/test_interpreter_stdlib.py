import os

from tests import InterpreterTestHelper


def test_clock():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'stdlib', 'clock.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '<fn:lox clock>' in out[0]
    assert [''] == err


def test_get_env():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'stdlib', 'get_env.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '<fn:lox get_env>' in out[0]
    assert [''] == err
