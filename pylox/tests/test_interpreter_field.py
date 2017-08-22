import os

from tests import InterpreterTestHelper


def test_call_function_field():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'call_function_field.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'bar' in out[0]
    assert [''] == err


def test_call_nonfunction_field():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'call_nonfunction_field.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Can only call functions and classes.' in err[0]
    assert '[line 6]' in err[1]


def test_get_on_bool():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'get_on_bool.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have properties.' in err[0]
    assert '[line 1]' in err[1]


def test_get_on_class():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'get_on_class.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have properties.' in err[0]
    assert '[line 2]' in err[1]


def test_get_on_function():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'get_on_function.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have properties.' in err[0]
    assert '[line 3]' in err[1]


def test_get_on_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'get_on_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have properties.' in err[0]
    assert '[line 1]' in err[1]


def test_get_on_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'get_on_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have properties.' in err[0]
    assert '[line 1]' in err[1]


def test_get_on_string():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'get_on_string.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have properties.' in err[0]
    assert '[line 1]' in err[1]


def test_many():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'many.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 79 == len(out)
    assert 'apple' in out[0]
    assert 'apricot' in out[1]
    assert 'avocado' in out[2]
    assert 'banana' in out[3]
    assert 'bilberry' in out[4]
    assert 'blackberry' in out[5]
    assert 'blackcurrant' in out[6]
    assert 'blueberry' in out[7]
    assert 'boysenberry' in out[8]
    assert 'cantaloupe' in out[9]
    assert 'cherimoya' in out[10]
    assert 'cherry' in out[11]
    assert 'clementine' in out[12]
    assert 'cloudberry' in out[13]
    assert 'coconut' in out[14]
    assert 'cranberry' in out[15]
    assert 'currant' in out[16]
    assert 'damson' in out[17]
    assert 'date' in out[18]
    assert 'dragonfruit' in out[19]
    assert 'durian' in out[20]
    assert 'elderberry' in out[21]
    assert 'feijoa' in out[22]
    assert 'fig' in out[23]
    assert 'gooseberry' in out[24]
    assert 'grape' in out[25]
    assert 'grapefruit' in out[26]
    assert 'guava' in out[27]
    assert 'honeydew' in out[28]
    assert 'huckleberry' in out[29]
    assert 'jabuticaba' in out[30]
    assert 'jackfruit' in out[31]
    assert 'jambul' in out[32]
    assert 'jujube' in out[33]
    assert 'juniper' in out[34]
    assert 'kiwifruit' in out[35]
    assert 'kumquat' in out[36]
    assert 'lemon' in out[37]
    assert 'lime' in out[38]
    assert 'longan' in out[39]
    assert 'loquat' in out[40]
    assert 'lychee' in out[41]
    assert 'mandarine' in out[42]
    assert 'mango' in out[43]
    assert 'marionberry' in out[44]
    assert 'melon' in out[45]
    assert 'miracle' in out[46]
    assert 'mulberry' in out[47]
    assert 'nance' in out[48]
    assert 'nectarine' in out[49]
    assert 'olive' in out[50]
    assert 'orange' in out[51]
    assert 'papaya' in out[52]
    assert 'passionfruit' in out[53]
    assert 'peach' in out[54]
    assert 'pear' in out[55]
    assert 'persimmon' in out[56]
    assert 'physalis' in out[57]
    assert 'pineapple' in out[58]
    assert 'plantain' in out[59]
    assert 'plum' in out[60]
    assert 'plumcot' in out[61]
    assert 'pomegranate' in out[62]
    assert 'pomelo' in out[63]
    assert 'quince' in out[64]
    assert 'raisin' in out[65]
    assert 'rambutan' in out[66]
    assert 'raspberry' in out[67]
    assert 'redcurrant' in out[68]
    assert 'salak' in out[69]
    assert 'salmonberry' in out[70]
    assert 'satsuma' in out[71]
    assert 'strawberry' in out[72]
    assert 'tamarillo' in out[73]
    assert 'tamarind' in out[74]
    assert 'tangerine' in out[75]
    assert 'tomato' in out[76]
    assert 'watermelon' in out[77]
    assert 'yuzu' in out[78]
    assert [''] == err


def test_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'got method' in out[0]
    assert 'arg' in out[1]
    assert [''] == err


def test_method_binds_this():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'method_binds_this.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'foo1' in out[0]
    assert [''] == err


def test_on_instance():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'on_instance.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 4 == len(out)
    assert 'bar value' in out[0]
    assert 'baz value' in out[1]
    assert 'bar value' in out[2]
    assert 'baz value' in out[3]
    assert [''] == err


def test_set_on_bool():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'set_on_bool.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have fields.' in err[0]
    assert '[line 1]' in err[1]


def test_set_on_class():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'set_on_class.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have fields.' in err[0]
    assert '[line 2]' in err[1]


def test_set_on_function():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'set_on_function.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have fields.' in err[0]
    assert '[line 3]' in err[1]


def test_set_on_nil():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'set_on_nil.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have fields.' in err[0]
    assert '[line 1]' in err[1]


def test_set_on_num():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'set_on_num.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have fields.' in err[0]
    assert '[line 1]' in err[1]


def test_set_on_string():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'set_on_string.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Only instances have fields.' in err[0]
    assert '[line 1]' in err[1]


def test_undefined():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'field', 'undefined.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined property \'bar\'.' in err[0]
    assert '[line 4]' in err[1]
