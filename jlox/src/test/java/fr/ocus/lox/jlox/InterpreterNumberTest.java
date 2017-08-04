package fr.ocus.lox.jlox;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class InterpreterNumberTest {

    @Test
    public void testLiterals() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/number/literals.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("123", out[0]);
        assertEquals("987654", out[1]);
        assertEquals("0", out[2]);
        assertEquals("-0", out[3]);
        assertEquals("123.456", out[4]);
        assertEquals("-0.001", out[5]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDecimalPointAtEof() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/number/decimal_point_at_eof.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testLeadingDot() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/number/leading_dot.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTrailingDot() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/number/trailing_dot.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
