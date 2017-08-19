package fr.ocus.lox.jlox;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class InterpreterStringTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterStringTest.class.getName());

    @Test
    public void testConcatenation() {
        Path file = Paths.get("src", "test", "resources", "programs", "string", "concatenation.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(6, out.length);
        assertEquals("ab", out[0]);
        assertEquals("c1.23", out[1]);
        assertEquals("4.56d", out[2]);
        assertEquals("e4.123456789012345", out[3]);
        assertEquals("2112", out[4]);
        assertEquals("202030", out[5]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testErrorAfterMultiline() {
        Path file = Paths.get("src", "test", "resources", "programs", "string", "error_after_multiline.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined variable 'err'."));
        assertThat(err[1], containsString("[line 7]"));
    }

    @Test
    public void testLiterals() {
        Path file = Paths.get("src", "test", "resources", "programs", "string", "literals.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(3, out.length);
        assertEquals("()", out[0]);
        assertEquals("a string", out[1]);
        assertEquals("A~¶Þॐஃ", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMultiline() {
        Path file = Paths.get("src", "test", "resources", "programs", "string", "multiline.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(3, out.length);
        assertEquals("1", out[0]);
        assertEquals("2", out[1]);
        assertEquals("3", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUnterminated() {
        Path file = Paths.get("src", "test", "resources", "programs", "string", "unterminated.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error: Unterminated string."));
    }
}
