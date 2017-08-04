package fr.ocus.lox.jlox;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class InterpreterForTest {

    @Test
    public void testClassInBody() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/class_in_body.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testStatementInitializer() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/statement_initializer.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testStatementCondition() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/statement_condition.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testVarInBody() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/var_in_body.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnClosure() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/return_closure.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("i", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testFunInBody() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/fun_in_body.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSyntax() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/syntax.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
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
    public void testScope() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/scope.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("0", out[0]);
        assertEquals("-1", out[1]);
        assertEquals("after", out[2]);
        assertEquals("0", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnInside() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/return_inside.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("i", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testClosureInBody() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/closure_in_body.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("1", out[0]);
        assertEquals("2", out[1]);
        assertEquals("3", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testStatementIncrement() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/for/statement_increment.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
