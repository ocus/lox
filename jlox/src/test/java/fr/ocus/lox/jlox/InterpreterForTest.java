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
public class InterpreterForTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterForTest.class.getName());

    @Test
    public void testClassInBody() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "class_in_body.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'class': Expect expression."));
    }

    @Test
    public void testClosureInBody() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "closure_in_body.lox");
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
    public void testFunInBody() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "fun_in_body.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'fun': Expect expression."));
    }

    @Test
    public void testReturnClosure() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "return_closure.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("i", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnInside() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "return_inside.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("i", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testScope() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "scope.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(4, out.length);
        assertEquals("0", out[0]);
        assertEquals("-1", out[1]);
        assertEquals("after", out[2]);
        assertEquals("0", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testStatementCondition() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "statement_condition.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Error at '{': Expect expression."));
        assertThat(err[1], containsString("Error at ')': Expect ';' after expression."));
    }

    @Test
    public void testStatementIncrement() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "statement_increment.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '{': Expect expression."));
    }

    @Test
    public void testStatementInitializer() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "statement_initializer.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Error at '{': Expect expression."));
        assertThat(err[1], containsString("Error at ')': Expect ';' after expression."));
    }

    @Test
    public void testSyntax() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "syntax.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(14, out.length);
        assertEquals("1", out[0]);
        assertEquals("2", out[1]);
        assertEquals("3", out[2]);
        assertEquals("0", out[3]);
        assertEquals("1", out[4]);
        assertEquals("2", out[5]);
        assertEquals("done", out[6]);
        assertEquals("0", out[7]);
        assertEquals("1", out[8]);
        assertEquals("0", out[9]);
        assertEquals("1", out[10]);
        assertEquals("2", out[11]);
        assertEquals("0", out[12]);
        assertEquals("1", out[13]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testVarInBody() {
        Path file = Paths.get("src", "test", "resources", "programs", "for", "var_in_body.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'var': Expect expression."));
    }
}
