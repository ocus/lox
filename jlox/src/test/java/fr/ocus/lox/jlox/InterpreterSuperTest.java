package fr.ocus.lox.jlox;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
@Ignore
public class InterpreterSuperTest {

    @Test
    public void testExtraArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/extra_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Derived.foo()", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperInClosureInInheritedMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/super_in_closure_in_inherited_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("A", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperInTopLevelFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/super_in_top_level_function.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/missing_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNoSuperclassMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/no_superclass_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testClosure() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/closure.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Base", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testThisInSuperclassMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/this_in_superclass_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("a", out[0]);
        assertEquals("b", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperAtTopLevel() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/super_at_top_level.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNoSuperclassCall() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/no_superclass_call.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperWithoutName() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/super_without_name.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testParenthesized() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/parenthesized.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallSameMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/call_same_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Derived.foo()", out[0]);
        assertEquals("Base.foo()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testBoundMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/bound_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("A.method(arg)", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperInInheritedMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/super_in_inherited_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("A", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperWithoutDot() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/super_without_dot.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNoSuperclassBind() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/no_superclass_bind.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testConstructor() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/constructor.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Derived.init()", out[0]);
        assertEquals("Base.init(a, b)", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallOtherMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/call_other_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Derived.bar()", out[0]);
        assertEquals("Base.foo()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReassignSuperclass() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/reassign_superclass.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Base.method()", out[0]);
        assertEquals("Base.method()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testIndirectlyInherited() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/super/indirectly_inherited.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("C.foo()", out[0]);
        assertEquals("A.foo()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }
}
