package fr.ocus.lox.jlox;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class InterpreterPrintTest {

    @Test
    public void testMissingArgument() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "print", "missing_argument.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
