package fr.ocus.lox.jlox;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
@Ignore
public class InterpreterThisTest {

    @Test
    public void testClosure() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "this", "closure.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNestedClass() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "this", "nested_class.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("Outer instance", out[0]);
        assertEquals("Outer instance", out[1]);
        assertEquals("Inner instance", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNestedClosure() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "this", "nested_closure.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testThisAtTopLevel() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "this", "this_at_top_level.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testThisInMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "this", "this_in_method.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("baz", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testThisInTopLevelFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "this", "this_in_top_level_function.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
