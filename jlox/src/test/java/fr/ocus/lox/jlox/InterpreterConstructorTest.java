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
public class InterpreterConstructorTest {

    @Test
    public void testArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("init", out[0]);
        assertEquals("1", out[1]);
        assertEquals("2", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallInitExplicitly() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "call_init_explicitly.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo.init(one)", out[0]);
        assertEquals("Foo.init(two)", out[1]);
        assertEquals("Foo instance", out[2]);
        assertEquals("init", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDefault() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "default.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo instance", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDefaultArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "default_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 0 arguments but got 3."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testEarlyReturn() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "early_return.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("init", out[0]);
        assertEquals("Foo instance", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testExtraArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "extra_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 4."));
        assertThat(err[1], containsString("[line 8]"));
    }

    @Test
    public void testMissingArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "missing_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 1."));
        assertThat(err[1], containsString("[line 5]"));
    }

    @Test
    public void testReturnInNestedFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "return_in_nested_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("bar", out[0]);
        assertEquals("Foo instance", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnValue() {
        Path file = Paths.get("src", "test", "resources", "programs", "constructor", "return_value.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'return': Cannot return a value from an initializer."));
    }
}
