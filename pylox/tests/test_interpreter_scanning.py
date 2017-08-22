import os

from tests import InterpreterTestHelper


def disabled_test_identifiers():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'scanning', 'identifiers.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 9 == len(out)
    assert 'IDENTIFIER andy null' in out[0]
    assert 'IDENTIFIER formless null' in out[1]
    assert 'IDENTIFIER fo null' in out[2]
    assert 'IDENTIFIER _ null' in out[3]
    assert 'IDENTIFIER _123 null' in out[4]
    assert 'IDENTIFIER _abc null' in out[5]
    assert 'IDENTIFIER ab123 null' in out[6]
    assert 'IDENTIFIER abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_ null' in out[7]
    assert 'EOF  null' in out[8]
    assert [''] == err


def disabled_test_keywords():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'scanning', 'keywords.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 16 == len(out)
    assert 'AND and null' in out[0]
    assert 'CLASS class null' in out[1]
    assert 'ELSE else null' in out[2]
    assert 'FALSE false null' in out[3]
    assert 'FOR for null' in out[4]
    assert 'FUN fun null' in out[5]
    assert 'IF if null' in out[6]
    assert 'NIL nil null' in out[7]
    assert 'OR or null' in out[8]
    assert 'RETURN return null' in out[9]
    assert 'SUPER super null' in out[10]
    assert 'THIS this null' in out[11]
    assert 'TRUE true null' in out[12]
    assert 'VAR var null' in out[13]
    assert 'WHILE while null' in out[14]
    assert 'EOF  null' in out[15]
    assert [''] == err


def disabled_test_numbers():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'scanning', 'numbers.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 7 == len(out)
    assert 'NUMBER 123 123.0' in out[0]
    assert 'NUMBER 123.456 123.456' in out[1]
    assert 'DOT . null' in out[2]
    assert 'NUMBER 456 456.0' in out[3]
    assert 'NUMBER 123 123.0' in out[4]
    assert 'DOT . null' in out[5]
    assert 'EOF  null' in out[6]
    assert [''] == err


def disabled_test_punctuators():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'scanning', 'punctuators.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 19 == len(out)
    assert 'LEFT_PAREN ( null' in out[0]
    assert 'RIGHT_PAREN ) null' in out[1]
    assert 'LEFT_BRACE { null' in out[2]
    assert 'RIGHT_BRACE } null' in out[3]
    assert 'SEMICOLON ; null' in out[4]
    assert 'COMMA , null' in out[5]
    assert 'PLUS + null' in out[6]
    assert 'MINUS - null' in out[7]
    assert 'STAR * null' in out[8]
    assert 'BANG_EQUAL != null' in out[9]
    assert 'EQUAL_EQUAL == null' in out[10]
    assert 'LESS_EQUAL <= null' in out[11]
    assert 'GREATER_EQUAL >= null' in out[12]
    assert 'BANG_EQUAL != null' in out[13]
    assert 'LESS < null' in out[14]
    assert 'GREATER > null' in out[15]
    assert 'SLASH / null' in out[16]
    assert 'DOT . null' in out[17]
    assert 'EOF  null' in out[18]
    assert [''] == err


def disabled_test_strings():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'scanning', 'strings.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 3 == len(out)
    assert 'STRING "" ' in out[0]
    assert 'STRING "string" string' in out[1]
    assert 'EOF  null' in out[2]
    assert [''] == err


def disabled_test_whitespace():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'scanning', 'whitespace.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 5 == len(out)
    assert 'IDENTIFIER space null' in out[0]
    assert 'IDENTIFIER tabs null' in out[1]
    assert 'IDENTIFIER newlines null' in out[2]
    assert 'IDENTIFIER end null' in out[3]
    assert 'EOF  null' in out[4]
    assert [''] == err
