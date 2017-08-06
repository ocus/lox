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
public class InterpreterFunctionTest {

    @Test
    public void testBodyMustBeBlock() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "body_must_be_block.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testEmptyBody() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "empty_body.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testExtraArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "extra_arguments.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testLocalMutualRecursion() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "local_mutual_recursion.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testLocalRecursion() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "local_recursion.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("21", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "missing_arguments.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingCommaInParameters() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "missing_comma_in_parameters.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMutualRecursion() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "mutual_recursion.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("true", out[0]);
        assertEquals("true", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testParameters() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "parameters.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("0", out[0]);
        assertEquals("1", out[1]);
        assertEquals("3", out[2]);
        assertEquals("6", out[3]);
        assertEquals("10", out[4]);
        assertEquals("15", out[5]);
        assertEquals("21", out[6]);
        assertEquals("28", out[7]);
        assertEquals("36", out[8]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testPrint() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "print.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("<fn foo>", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testRecursion() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "recursion.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("21", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTooManyArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "too_many_arguments.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTooManyParameters() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "function", "too_many_parameters.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
