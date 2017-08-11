package fr.ocus.lox.jlox;

import org.junit.Ignore;
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
@Ignore
public class InterpreterInheritanceTest {

    @Test
    public void testInheritFromFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "inheritance", "inherit_from_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Superclass must be a class."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testInheritFromNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "inheritance", "inherit_from_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Superclass must be a class."));
        assertThat(err[1], containsString("[line 2]"));
    }

    @Test
    public void testInheritFromNumber() {
        Path file = Paths.get("src", "test", "resources", "programs", "inheritance", "inherit_from_number.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Superclass must be a class."));
        assertThat(err[1], containsString("[line 2]"));
    }

    @Test
    public void testInheritMethods() {
        Path file = Paths.get("src", "test", "resources", "programs", "inheritance", "inherit_methods.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("foo", out[0]);
        assertEquals("bar", out[1]);
        assertEquals("bar", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testParenthesizedSuperclass() {
        Path file = Paths.get("src", "test", "resources", "programs", "inheritance", "parenthesized_superclass.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at '(': Expect superclass name."));
    }

    @Test
    public void testSetFieldsFromBaseClass() {
        Path file = Paths.get("src", "test", "resources", "programs", "inheritance", "set_fields_from_base_class.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("foo 1", out[0]);
        assertEquals("foo 2", out[1]);
        assertEquals("bar 1", out[2]);
        assertEquals("bar 2", out[3]);
        assertEquals("bar 1", out[4]);
        assertEquals("bar 2", out[5]);
        assertArrayEquals(new String[]{""}, err);
    }
}
