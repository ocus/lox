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
public class InterpreterThisTest {

    @Test
    public void testClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "this", "closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNestedClass() {
        Path file = Paths.get("src", "test", "resources", "programs", "this", "nested_class.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Outer instance", out[0]);
        assertEquals("Outer instance", out[1]);
        assertEquals("Inner instance", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNestedClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "this", "nested_closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testThisAtTopLevel() {
        Path file = Paths.get("src", "test", "resources", "programs", "this", "this_at_top_level.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'this': Cannot use 'this' outside of a class."));
    }

    @Test
    public void testThisInMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "this", "this_in_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("baz", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testThisInTopLevelFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "this", "this_in_top_level_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'this': Cannot use 'this' outside of a class."));
    }
}
