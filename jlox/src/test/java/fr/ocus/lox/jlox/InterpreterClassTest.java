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
public class InterpreterClassTest {

    @Test
    public void testEmpty() {
        Path file = Paths.get("src", "test", "resources", "programs", "class", "empty.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInheritedMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "class", "inherited_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("in foo", out[0]);
        assertEquals("in bar", out[1]);
        assertEquals("in baz", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testLocalReferenceSelf() {
        Path file = Paths.get("src", "test", "resources", "programs", "class", "local_reference_self.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testReferenceSelf() {
        Path file = Paths.get("src", "test", "resources", "programs", "class", "reference_self.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("Foo", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
