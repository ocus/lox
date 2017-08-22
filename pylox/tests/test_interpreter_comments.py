import os

from tests import InterpreterTestHelper


def test_line_at_eof():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'comments', 'line_at_eof.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'ok' in out[0]
    assert [''] == err


def test_only_line_comment():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'comments', 'only_line_comment.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def test_only_line_comment_and_line():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'comments', 'only_line_comment_and_line.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert [''] == err


def test_unicode():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'comments', 'unicode.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'ok' in out[0]
    assert [''] == err
