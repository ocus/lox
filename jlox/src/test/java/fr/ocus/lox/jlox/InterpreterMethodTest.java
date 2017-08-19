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
public class InterpreterMethodTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterMethodTest.class.getName());

    @Test
    public void testArity() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "arity.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(9, out.length);
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

    @Test
    public void testEmptyBlock() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "empty_block.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testExtraArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "extra_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 4."));
        assertThat(err[1], containsString("[line 8]"));
    }

    @Test
    public void testMissingArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "missing_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Expected 2 arguments but got 1."));
        assertThat(err[1], containsString("[line 5]"));
    }

    @Test
    public void testNotFound() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "not_found.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined property 'unknown'."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testReferToName() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "refer_to_name.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined variable 'method'."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testTooManyArguments() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "too_many_arguments.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '9': Cannot have more than 8 arguments."));
    }

    @Test
    public void testTooManyParameters() {
        Path file = Paths.get("src", "test", "resources", "programs", "method", "too_many_parameters.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'i': Cannot have more than 8 parameters."));
    }
}
