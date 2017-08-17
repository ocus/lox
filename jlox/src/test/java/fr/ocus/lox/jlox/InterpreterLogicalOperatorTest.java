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
public class InterpreterLogicalOperatorTest {

    @Test
    public void testAnd() {
        Path file = Paths.get("src", "test", "resources", "programs", "logical_operator", "and.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(7, out.length);
        assertEquals("false", out[0]);
        assertEquals("1", out[1]);
        assertEquals("false", out[2]);
        assertEquals("true", out[3]);
        assertEquals("3", out[4]);
        assertEquals("true", out[5]);
        assertEquals("false", out[6]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAndTruth() {
        Path file = Paths.get("src", "test", "resources", "programs", "logical_operator", "and_truth.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(5, out.length);
        assertEquals("false", out[0]);
        assertEquals("nil", out[1]);
        assertEquals("ok", out[2]);
        assertEquals("ok", out[3]);
        assertEquals("ok", out[4]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testOr() {
        Path file = Paths.get("src", "test", "resources", "programs", "logical_operator", "or.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(7, out.length);
        assertEquals("1", out[0]);
        assertEquals("1", out[1]);
        assertEquals("true", out[2]);
        assertEquals("false", out[3]);
        assertEquals("false", out[4]);
        assertEquals("false", out[5]);
        assertEquals("true", out[6]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testOrTruth() {
        Path file = Paths.get("src", "test", "resources", "programs", "logical_operator", "or_truth.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(5, out.length);
        assertEquals("ok", out[0]);
        assertEquals("ok", out[1]);
        assertEquals("true", out[2]);
        assertEquals("0", out[3]);
        assertEquals("s", out[4]);
        assertArrayEquals(new String[]{""}, err);
    }
}
