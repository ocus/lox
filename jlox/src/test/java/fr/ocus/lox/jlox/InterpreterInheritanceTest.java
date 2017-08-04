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
public class InterpreterInheritanceTest {

    @Test
    public void testSetFieldsFromBaseClass() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/inheritance/set_fields_from_base_class.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("foo 1", out[0]);
        assertEquals("foo 2", out[1]);
        assertEquals("bar 1", out[2]);
        assertEquals("bar 2", out[3]);
        assertEquals("bar 1", out[4]);
        assertEquals("bar 2", out[5]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInheritFromNil() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/inheritance/inherit_from_nil.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInheritFromNumber() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/inheritance/inherit_from_number.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInheritFromFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/inheritance/inherit_from_function.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInheritMethods() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/inheritance/inherit_methods.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("foo", out[0]);
        assertEquals("bar", out[1]);
        assertEquals("bar", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testParenthesizedSuperclass() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/inheritance/parenthesized_superclass.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }
}
