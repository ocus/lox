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
public class InterpreterClosureTest {

    @Test
    public void testAssignToClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "assign_to_closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("local", out[0]);
        assertEquals("after f", out[1]);
        assertEquals("after f", out[2]);
        assertEquals("after g", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAssignToShadowedLater() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "assign_to_shadowed_later.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("inner", out[0]);
        assertEquals("assigned", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testClosedClosureInFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "closed_closure_in_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("local", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCloseOverFunctionParameter() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "close_over_function_parameter.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("param", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCloseOverLaterVariable() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "close_over_later_variable.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("b", out[0]);
        assertEquals("a", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCloseOverMethodParameter() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "close_over_method_parameter.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("param", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNestedClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "nested_closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("a", out[0]);
        assertEquals("b", out[1]);
        assertEquals("c", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testOpenClosureInFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "open_closure_in_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("local", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReferenceClosureMultipleTimes() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "reference_closure_multiple_times.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("a", out[0]);
        assertEquals("a", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReuseClosureSlot() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "reuse_closure_slot.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("a", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowClosureWithLocal() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "shadow_closure_with_local.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("closure", out[0]);
        assertEquals("shadow", out[1]);
        assertEquals("closure", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUnusedClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "closure", "unused_closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
