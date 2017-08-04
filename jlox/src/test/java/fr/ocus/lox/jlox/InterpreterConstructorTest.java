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
    @Ignore
public class InterpreterConstructorTest {

    @Test
    public void testDefaultArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/default_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testExtraArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/extra_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMissingArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/missing_arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testEarlyReturn() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/early_return.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("init", out[0]);
        assertEquals("Foo instance", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDefault() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/default.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Foo instance", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testArguments() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/arguments.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("init", out[0]);
        assertEquals("1", out[1]);
        assertEquals("2", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnInNestedFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/return_in_nested_function.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("bar", out[0]);
        assertEquals("Foo instance", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReturnValue() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/return_value.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallInitExplicitly() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/constructor/call_init_explicitly.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("Foo.init(one)", out[0]);
        assertEquals("Foo.init(two)", out[1]);
        assertEquals("Foo instance", out[2]);
        assertEquals("init", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }
}
