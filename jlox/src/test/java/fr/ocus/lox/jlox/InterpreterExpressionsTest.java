package fr.ocus.lox.jlox;

import org.junit.Ignore;
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
public class InterpreterExpressionsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterExpressionsTest.class.getName());

    @Test
    @Ignore
    public void testEvaluate() {
        Path file = Paths.get("src", "test", "resources", "programs", "expressions", "evaluate.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("2", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    @Ignore
    public void testParse() {
        Path file = Paths.get("src", "test", "resources", "programs", "expressions", "parse.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("(+ (group (- 5.0 (group (- 3.0 1.0)))) (- 1.0))", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
