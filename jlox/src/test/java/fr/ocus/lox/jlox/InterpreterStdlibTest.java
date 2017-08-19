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
public class InterpreterStdlibTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterStdlibTest.class.getName());

    @Test
    public void testClock() {
        Path file = Paths.get("src", "test", "resources", "programs", "stdlib", "clock.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("<fn:lox clock>", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGetEnv() {
        Path file = Paths.get("src", "test", "resources", "programs", "stdlib", "get_env.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("<fn:lox get_env>", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
