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
public class InterpreterReturnTest {

    @Test
    public void testAfterElse() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "after_else.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAfterIf() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "after_if.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAfterWhile() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "after_while.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAtTopLevel() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "at_top_level.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'return': Cannot return from top-level code."));
    }

    @Test
    public void testInFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "in_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "in_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnNilIfNoValue() {
        Path file = Paths.get("src", "test", "resources", "programs", "return", "return_nil_if_no_value.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
