import os

from tests import InterpreterTestHelper


def disabled_test_evaluate():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'expressions', 'evaluate.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '2' in out[0]
    assert [''] == err


def disabled_test_parse():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'expressions', 'parse.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert '(+ (group (- 5.0 (group (- 3.0 1.0)))) (- 1.0))' in out[0]
    assert [''] == err
