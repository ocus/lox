import os

from tests import InterpreterTestHelper


def test_bound_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'bound_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'A.method(arg)' in out[0]
    assert [''] == err


def test_call_other_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'call_other_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'Derived.bar()' in out[0]
    assert 'Base.foo()' in out[1]
    assert [''] == err


def test_call_same_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'call_same_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'Derived.foo()' in out[0]
    assert 'Base.foo()' in out[1]
    assert [''] == err


def test_closure():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'closure.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'Base' in out[0]
    assert [''] == err


def test_constructor():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'constructor.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'Derived.init()' in out[0]
    assert 'Base.init(a, b)' in out[1]
    assert [''] == err


def test_extra_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'extra_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'Derived.foo()' in out[0]
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 4.' in err[0]
    assert '[line 10]' in err[1]


def test_indirectly_inherited():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'indirectly_inherited.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'C.foo()' in out[0]
    assert 'A.foo()' in out[1]
    assert [''] == err


def test_missing_arguments():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'missing_arguments.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Expected 2 arguments but got 1.' in err[0]
    assert '[line 9]' in err[1]


def test_no_superclass_bind():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'no_superclass_bind.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'super\': Cannot use \'super\' in a class with no superclass.' in err[0]


def test_no_superclass_call():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'no_superclass_call.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'super\': Cannot use \'super\' in a class with no superclass.' in err[0]


def test_no_superclass_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'no_superclass_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Undefined property \'doesNotExist\'.' in err[0]
    assert '[line 5]' in err[1]


def test_parenthesized():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'parenthesized.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \')\': Expect \'.\' after \'super\'.' in err[0]


def test_reassign_superclass():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'reassign_superclass.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'Base.method()' in out[0]
    assert 'Base.method()' in out[1]
    assert [''] == err


def test_super_at_top_level():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'super_at_top_level.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 2 == len(err)
    assert 'Error at \'super\': Cannot use \'super\' outside of a class.' in err[0]
    assert 'Error at \'super\': Cannot use \'super\' outside of a class.' in err[1]


def test_super_in_closure_in_inherited_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'super_in_closure_in_inherited_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'A' in out[0]
    assert [''] == err


def test_super_in_inherited_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'super_in_inherited_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 1 == len(out)
    assert 'A' in out[0]
    assert [''] == err


def test_super_in_top_level_function():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'super_in_top_level_function.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \'super\': Cannot use \'super\' outside of a class.' in err[0]


def test_super_without_dot():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'super_without_dot.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \';\': Expect \'.\' after \'super\'.' in err[0]


def test_super_without_name():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'super_without_name.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert [''] == out
    assert 1 == len(err)
    assert 'Error at \';\': Expect superclass method name.' in err[0]


def test_this_in_superclass_method():
    helper = InterpreterTestHelper(
        source_file=os.path.join(
            '..', 'jlox', 'src', 'test', 'resources', 'programs', 'super', 'this_in_superclass_method.lox'
        )
    )
    helper.run()
    out = helper.get_output()
    err = helper.get_error()
    assert 2 == len(out)
    assert 'a' in out[0]
    assert 'b' in out[1]
    assert [''] == err
