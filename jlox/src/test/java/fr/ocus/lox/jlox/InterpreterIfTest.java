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
public class InterpreterIfTest {

    @Test
    public void testClassInElse() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "class_in_else.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'class': Expect expression."));
    }

    @Test
    public void testClassInThen() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "class_in_then.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'class': Expect expression."));
    }

    @Test
    public void testDanglingElse() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "dangling_else.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("good", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testElse() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "else.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(3, out.length);
        assertEquals("good", out[0]);
        assertEquals("good", out[1]);
        assertEquals("block", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testFunInElse() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "fun_in_else.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'fun': Expect expression."));
    }

    @Test
    public void testFunInThen() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "fun_in_then.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'fun': Expect expression."));
    }

    @Test
    public void testIf() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "if.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(3, out.length);
        assertEquals("good", out[0]);
        assertEquals("block", out[1]);
        assertEquals("true", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTruth() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "truth.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(5, out.length);
        assertEquals("false", out[0]);
        assertEquals("nil", out[1]);
        assertEquals("true", out[2]);
        assertEquals("0", out[3]);
        assertEquals("empty", out[4]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testVarInElse() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "var_in_else.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'var': Expect expression."));
    }

    @Test
    public void testVarInThen() {
        Path file = Paths.get("src", "test", "resources", "programs", "if", "var_in_then.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'var': Expect expression."));
    }
}
