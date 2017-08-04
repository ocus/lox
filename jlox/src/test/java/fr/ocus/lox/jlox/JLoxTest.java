package fr.ocus.lox.jlox;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class JLoxTest {

    @Test
    public void testInterpreter() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/precedence.lox");
        helper.run();

        String[] out = helper.getOutput();
        String[] err = helper.getError();
        assertArrayEquals(new String[] {""}, err);

        assertEquals("14", out[0]);
        assertEquals("8", out[1]);
        assertEquals("4", out[2]);
        assertEquals("0", out[3]);
        System.out.print(out);
        System.err.print("//"+ Arrays.toString(err)+"//");
    }
}
