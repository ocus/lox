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
public class InterpreterRegressionTest {

    @Test
    public void test40() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/regression/40.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("false", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
