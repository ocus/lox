package fr.ocus.lox.jlox;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class InterpreterSuperTest {

    @Test
    public void testBoundMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "bound_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("A.method(arg)", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallOtherMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "call_other_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("Derived.bar()", out[0]);
        assertEquals("Base.foo()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallSameMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "call_same_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("Derived.foo()", out[0]);
        assertEquals("Base.foo()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("Base", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testConstructor() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "constructor.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("Derived.init()", out[0]);
        assertEquals("Base.init(a, b)", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testExtraArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "extra_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("Derived.foo()", out[0]);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 4."));
        assertThat(err[1], containsString("[line 10]"));
    }

    @Test
    public void testIndirectlyInherited() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "indirectly_inherited.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("C.foo()", out[0]);
        assertEquals("A.foo()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "missing_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 1."));
        assertThat(err[1], containsString("[line 9]"));
    }

    @Test
    public void testNoSuperclassBind() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "no_superclass_bind.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'super': Cannot use 'super' in a class with no superclass."));
    }

    @Test
    public void testNoSuperclassCall() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "no_superclass_call.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'super': Cannot use 'super' in a class with no superclass."));
    }

    @Test
    public void testNoSuperclassMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "no_superclass_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined property 'doesNotExist'."));
        assertThat(err[1], containsString("[line 5]"));
    }

    @Test
    public void testParenthesized() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "parenthesized.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at ')': Expect '.' after 'super'."));
    }

    @Test
    public void testReassignSuperclass() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "reassign_superclass.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("Base.method()", out[0]);
        assertEquals("Base.method()", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperAtTopLevel() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "super_at_top_level.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Error at 'super': Cannot use 'super' outside of a class."));
        assertThat(err[1], containsString("Error at 'super': Cannot use 'super' outside of a class."));
    }

    @Test
    public void testSuperInClosureInInheritedMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "super_in_closure_in_inherited_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("A", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperInInheritedMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "super_in_inherited_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("A", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSuperInTopLevelFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "super_in_top_level_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'super': Cannot use 'super' outside of a class."));
    }

    @Test
    public void testSuperWithoutDot() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "super_without_dot.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at ';': Expect '.' after 'super'."));
    }

    @Test
    public void testSuperWithoutName() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "super_without_name.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at ';': Expect superclass method name."));
    }

    @Test
    public void testThisInSuperclassMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "super", "this_in_superclass_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("b", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }
}
