package fr.ocus.lox.jlox;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class InterpreterBoolTest {

    @Test
    public void testEquality() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/bool/equality.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("true", out[0]);
        assertEquals("false", out[1]);
        assertEquals("false", out[2]);
        assertEquals("true", out[3]);
        assertEquals("false", out[4]);
        assertEquals("false", out[5]);
        assertEquals("false", out[6]);
        assertEquals("false", out[7]);
        assertEquals("false", out[8]);
        assertEquals("false", out[9]);
        assertEquals("true", out[10]);
        assertEquals("true", out[11]);
        assertEquals("false", out[12]);
        assertEquals("true", out[13]);
        assertEquals("true", out[14]);
        assertEquals("true", out[15]);
        assertEquals("true", out[16]);
        assertEquals("true", out[17]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNot() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/bool/not.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("false", out[0]);
        assertEquals("true", out[1]);
        assertEquals("true", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }
}
