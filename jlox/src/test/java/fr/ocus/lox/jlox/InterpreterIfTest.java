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
public class InterpreterIfTest {

    @Test
    public void testClassInElse() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "class_in_else.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testClassInThen() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "class_in_then.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDanglingElse() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "dangling_else.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("good", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testElse() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "else.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("good", out[0]);
        assertEquals("good", out[1]);
        assertEquals("block", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testFunInElse() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "fun_in_else.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testFunInThen() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "fun_in_then.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testIf() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "if.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("good", out[0]);
        assertEquals("block", out[1]);
        assertEquals("true", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testTruth() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "truth.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertEquals("false", out[0]);
        assertEquals("nil", out[1]);
        assertEquals("true", out[2]);
        assertEquals("0", out[3]);
        assertEquals("empty", out[4]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testVarInElse() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "var_in_else.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testVarInThen() {
        InterpreterTestHelper helper = new InterpreterTestHelper(Paths.get("src", "test", "resources", "programs", "if", "var_in_then.lox"));
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println("OUT: " + Arrays.toString(out));
        System.err.println("ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
