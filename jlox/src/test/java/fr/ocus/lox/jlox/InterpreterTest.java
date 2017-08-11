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
public class InterpreterTest {

    @Test
    public void testEmptyFile() {
        Path file = Paths.get("src", "test", "resources", "programs", "empty_file.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testPrecedence() {
        Path file = Paths.get("src", "test", "resources", "programs", "precedence.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("14", out[0]);
        assertEquals("8", out[1]);
        assertEquals("4", out[2]);
        assertEquals("0", out[3]);
        assertEquals("true", out[4]);
        assertEquals("true", out[5]);
        assertEquals("true", out[6]);
        assertEquals("true", out[7]);
        assertEquals("0", out[8]);
        assertEquals("0", out[9]);
        assertEquals("0", out[10]);
        assertEquals("0", out[11]);
        assertEquals("4", out[12]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUnexpectedCharacter() {
        Path file = Paths.get("src", "test", "resources", "programs", "unexpected_character.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Error: Unexpected character."));
        assertThat(err[1], containsString("Error at 'b': Expect ')' after arguments."));
    }
}
