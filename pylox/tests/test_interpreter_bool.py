import os

from tests import InterpreterTestHelper


def test_equality():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'bool', 'equality.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 18 == len(out)
    assert 'true' in out[0]
    assert 'false' in out[1]
    assert 'false' in out[2]
    assert 'true' in out[3]
    assert 'false' in out[4]
    assert 'false' in out[5]
    assert 'false' in out[6]
    assert 'false' in out[7]
    assert 'false' in out[8]
    assert 'false' in out[9]
    assert 'true' in out[10]
    assert 'true' in out[11]
    assert 'false' in out[12]
    assert 'true' in out[13]
    assert 'true' in out[14]
    assert 'true' in out[15]
    assert 'true' in out[16]
    assert 'true' in out[17]
    assert [''] == err


def test_not():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'bool', 'not.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert 'false' in out[0]
    assert 'true' in out[1]
    assert 'true' in out[2]
    assert [''] == err
