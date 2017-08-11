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
public class InterpreterAssignmentTest {

    @Test
    public void testAssociativity() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "associativity.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("c", out[0]);
        assertEquals("c", out[1]);
        assertEquals("c", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGlobal() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "global.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("before", out[0]);
        assertEquals("after", out[1]);
        assertEquals("arg", out[2]);
        assertEquals("arg", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGrouping() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "grouping.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '=': Invalid assignment target."));
    }

    @Test
    public void testInfixOperator() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "infix_operator.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '=': Invalid assignment target."));
    }

    @Test
    public void testLocal() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "local.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("before", out[0]);
        assertEquals("after", out[1]);
        assertEquals("arg", out[2]);
        assertEquals("arg", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testPrefixOperator() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "prefix_operator.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '=': Invalid assignment target."));
    }

    @Test
    public void testSyntax() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "syntax.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("var", out[0]);
        assertEquals("var", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testToThis() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "to_this.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '=': Invalid assignment target."));
    }

    @Test
    public void testUndefined() {
        Path file = Paths.get("src", "test", "resources", "programs", "assignment", "undefined.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined variable 'unknown'."));
        assertThat(err[1], containsString("[line 1]"));
    }
}
