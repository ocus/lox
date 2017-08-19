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
public class InterpreterPrintTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterPrintTest.class.getName());

    @Test
    public void testMissingArgument() {
        Path file = Paths.get("src", "test", "resources", "programs", "print", "missing_argument.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at ';': Expect expression."));
    }
}
