import os

from tests import InterpreterTestHelper


def test_add():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert '579' in out[0]
    assert 'string' in out[1]
    assert [''] == err


def test_add_bool_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add_bool_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be two numbers or two strings.' in err[0]
    assert '[line 1]' in err[1]


def test_add_bool_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add_bool_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be two numbers or two strings.' in err[0]
    assert '[line 1]' in err[1]


def test_add_bool_string():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add_bool_string.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be two numbers or two strings.' in err[0]
    assert '[line 1]' in err[1]


def test_add_nil_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add_nil_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be two numbers or two strings.' in err[0]
    assert '[line 1]' in err[1]


def test_add_num_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add_num_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be two numbers or two strings.' in err[0]
    assert '[line 1]' in err[1]


def test_add_string_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'add_string_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be two numbers or two strings.' in err[0]
    assert '[line 1]' in err[1]


def test_comparison():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'comparison.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 20 == len(out)
    assert 'true' in out[0]
    assert 'false' in out[1]
    assert 'false' in out[2]
    assert 'true' in out[3]
    assert 'true' in out[4]
    assert 'false' in out[5]
    assert 'false' in out[6]
    assert 'false' in out[7]
    assert 'true' in out[8]
    assert 'false' in out[9]
    assert 'true' in out[10]
    assert 'true' in out[11]
    assert 'false' in out[12]
    assert 'false' in out[13]
    assert 'false' in out[14]
    assert 'false' in out[15]
    assert 'true' in out[16]
    assert 'true' in out[17]
    assert 'true' in out[18]
    assert 'true' in out[19]
    assert [''] == err


def test_divide():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'divide.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert '4' in out[0]
    assert '1' in out[1]
    assert [''] == err


def test_divide_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'divide_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_divide_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'divide_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_equals():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'equals.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 10 == len(out)
    assert 'true' in out[0]
    assert 'true' in out[1]
    assert 'false' in out[2]
    assert 'true' in out[3]
    assert 'false' in out[4]
    assert 'true' in out[5]
    assert 'false' in out[6]
    assert 'false' in out[7]
    assert 'false' in out[8]
    assert 'false' in out[9]
    assert [''] == err


def test_equals_class():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'equals_class.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'true' in out[0]
    assert 'false' in out[1]
    assert [''] == err


def test_greater_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'greater_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_greater_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'greater_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_greater_or_equal_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'greater_or_equal_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_greater_or_equal_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'greater_or_equal_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_less_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'less_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_less_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'less_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_less_or_equal_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'less_or_equal_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_less_or_equal_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'less_or_equal_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_multiply():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'multiply.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert '15' in out[0]
    assert '3.702' in out[1]
    assert [''] == err


def test_multiply_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'multiply_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_multiply_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'multiply_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_negate():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'negate.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert '-3' in out[0]
    assert '3' in out[1]
    assert '-3' in out[2]
    assert [''] == err


def test_negate_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'negate_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operand must be a number.' in err[0]
    assert '[line 1]' in err[1]


def test_not():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'not.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 8 == len(out)
    assert 'false' in out[0]
    assert 'true' in out[1]
    assert 'true' in out[2]
    assert 'false' in out[3]
    assert 'false' in out[4]
    assert 'true' in out[5]
    assert 'false' in out[6]
    assert 'false' in out[7]
    assert [''] == err


def test_not_class():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'not_class.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'false' in out[0]
    assert 'false' in out[1]
    assert [''] == err


def test_not_equals():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'not_equals.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 10 == len(out)
    assert 'false' in out[0]
    assert 'false' in out[1]
    assert 'true' in out[2]
    assert 'false' in out[3]
    assert 'true' in out[4]
    assert 'false' in out[5]
    assert 'true' in out[6]
    assert 'true' in out[7]
    assert 'true' in out[8]
    assert 'true' in out[9]
    assert [''] == err


def test_subtract():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'subtract.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert '1' in out[0]
    assert '0' in out[1]
    assert [''] == err


def test_subtract_nonnum_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'subtract_nonnum_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]


def test_subtract_num_nonnum():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'operator', 'subtract_num_nonnum.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Operands must be numbers.' in err[0]
    assert '[line 1]' in err[1]
