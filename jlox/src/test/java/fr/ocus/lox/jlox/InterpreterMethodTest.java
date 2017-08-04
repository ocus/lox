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
public class InterpreterMethodTest {

    @Test
    public void testReferToName() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/refer_to_name.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testExtraArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/extra_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTooManyParameters() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/too_many_parameters.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/missing_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTooManyArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/too_many_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNotFound() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/not_found.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testEmptyBlock() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/empty_block.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testArity() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/method/arity.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("no args", out[0]);
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
}
