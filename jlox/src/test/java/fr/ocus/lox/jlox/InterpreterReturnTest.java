package fr.ocus.lox.jlox;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class InterpreterReturnTest {

    @Test
    public void testAfterElse() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "after_else.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAfterIf() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "after_if.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAfterWhile() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "after_while.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAtTopLevel() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "at_top_level.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "in_function.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "in_method.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnNilIfNoValue() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "return", "return_nil_if_no_value.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
