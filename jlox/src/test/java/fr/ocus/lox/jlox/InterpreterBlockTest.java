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
public class InterpreterBlockTest {

    @Test
    public void testEmpty() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/block/empty.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testScope() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/block/scope.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("inner", out[0]);
        assertEquals("outer", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }
}
