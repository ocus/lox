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
@Ignore
public class InterpreterExpressionsTest {

    @Test
    public void testEvaluate() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/expressions/evaluate.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("2", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testParse() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/expressions/parse.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("(+ (group (- 5.0 (group (- 3.0 1.0)))) (- 1.0))", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
