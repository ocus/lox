import os

from tests import InterpreterTestHelper


def test_empty_file():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'empty_file.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def test_precedence():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'precedence.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 13 == len(out)
    assert '14' in out[0]
    assert '8' in out[1]
    assert '4' in out[2]
    assert '0' in out[3]
    assert 'true' in out[4]
    assert 'true' in out[5]
    assert 'true' in out[6]
    assert 'true' in out[7]
    assert '0' in out[8]
    assert '0' in out[9]
    assert '0' in out[10]
    assert '0' in out[11]
    assert '4' in out[12]
    assert [''] == err


def test_unexpected_character():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'unexpected_character.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Error: Unexpected character.' in err[0]
    assert 'Error at \'b\': Expect \')\' after arguments.' in err[1]
