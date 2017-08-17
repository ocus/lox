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
public class InterpreterNumberTest {

    @Test
    public void testDecimalPointAtEof() {
        Path file = Paths.get("src", "test", "resources", "programs", "number", "decimal_point_at_eof.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at end: Expect property name after '.'."));
    }

    @Test
    public void testLeadingDot() {
        Path file = Paths.get("src", "test", "resources", "programs", "number", "leading_dot.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '.': Expect expression."));
    }

    @Test
    public void testLiterals() {
        Path file = Paths.get("src", "test", "resources", "programs", "number", "literals.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(6, out.length);
        assertEquals("123", out[0]);
        assertEquals("987654", out[1]);
        assertEquals("0", out[2]);
        assertEquals("-0", out[3]);
        assertEquals("123.456", out[4]);
        assertEquals("-0.001", out[5]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTrailingDot() {
        Path file = Paths.get("src", "test", "resources", "programs", "number", "trailing_dot.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at ';': Expect property name after '.'."));
    }
}
