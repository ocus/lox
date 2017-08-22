import os

from tests import InterpreterTestHelper


def test_and():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'logical_operator', 'and.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 7 == len(out)
    assert 'false' in out[0]
    assert '1' in out[1]
    assert 'false' in out[2]
    assert 'true' in out[3]
    assert '3' in out[4]
    assert 'true' in out[5]
    assert 'false' in out[6]
    assert [''] == err


def test_and_truth():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'logical_operator', 'and_truth.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 5 == len(out)
    assert 'false' in out[0]
    assert 'nil' in out[1]
    assert 'ok' in out[2]
    assert 'ok' in out[3]
    assert 'ok' in out[4]
    assert [''] == err


def test_or():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'logical_operator', 'or.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 7 == len(out)
    assert '1' in out[0]
    assert '1' in out[1]
    assert 'true' in out[2]
    assert 'false' in out[3]
    assert 'false' in out[4]
    assert 'false' in out[5]
    assert 'true' in out[6]
    assert [''] == err


def test_or_truth():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'logical_operator', 'or_truth.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 5 == len(out)
    assert 'ok' in out[0]
    assert 'ok' in out[1]
    assert 'true' in out[2]
    assert '0' in out[3]
    assert 's' in out[4]
    assert [''] == err
