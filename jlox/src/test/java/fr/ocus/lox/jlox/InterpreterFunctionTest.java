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
public class InterpreterFunctionTest {

    @Test
    public void testBodyMustBeBlock() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "body_must_be_block.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '123': Expect '{' before function body."));
    }

    @Test
    public void testEmptyBody() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "empty_body.lox");
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

    @Test
    public void testExtraArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "extra_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 4."));
        assertThat(err[1], containsString("[line 6]"));
    }

    @Test
    public void testLocalMutualRecursion() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "local_mutual_recursion.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined variable 'isOdd'."));
        assertThat(err[1], containsString("[line 4]"));
    }

    @Test
    public void testLocalRecursion() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "local_recursion.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("21", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "missing_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 1."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testMissingCommaInParameters() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "missing_comma_in_parameters.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'c': Expect ')' after parameters."));
    }

    @Test
    public void testMutualRecursion() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "mutual_recursion.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("true", out[0]);
        assertEquals("true", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testParameters() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "parameters.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(9, out.length);
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
        Path file = Paths.get("src", "test", "resources", "programs", "function", "print.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("<fn foo>", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testRecursion() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "recursion.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("21", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTooManyArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "too_many_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '9': Cannot have more than 8 arguments."));
    }

    @Test
    public void testTooManyParameters() {
        Path file = Paths.get("src", "test", "resources", "programs", "function", "too_many_parameters.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'i': Cannot have more than 8 parameters."));
    }
}
