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
public class InterpreterFieldTest {

    @Test
    public void testCallFunctionField() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "call_function_field.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("bar", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallNonfunctionField() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "call_nonfunction_field.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Can only call functions and classes."));
        assertThat(err[1], containsString("[line 6]"));
    }

    @Test
    public void testGetOnBool() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "get_on_bool.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have properties."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testGetOnClass() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "get_on_class.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have properties."));
        assertThat(err[1], containsString("[line 2]"));
    }

    @Test
    public void testGetOnFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "get_on_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have properties."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testGetOnNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "get_on_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have properties."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testGetOnNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "get_on_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have properties."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testGetOnString() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "get_on_string.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have properties."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testMany() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "many.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(79, out.length);
        assertEquals("apple", out[0]);
        assertEquals("apricot", out[1]);
        assertEquals("avocado", out[2]);
        assertEquals("banana", out[3]);
        assertEquals("bilberry", out[4]);
        assertEquals("blackberry", out[5]);
        assertEquals("blackcurrant", out[6]);
        assertEquals("blueberry", out[7]);
        assertEquals("boysenberry", out[8]);
        assertEquals("cantaloupe", out[9]);
        assertEquals("cherimoya", out[10]);
        assertEquals("cherry", out[11]);
        assertEquals("clementine", out[12]);
        assertEquals("cloudberry", out[13]);
        assertEquals("coconut", out[14]);
        assertEquals("cranberry", out[15]);
        assertEquals("currant", out[16]);
        assertEquals("damson", out[17]);
        assertEquals("date", out[18]);
        assertEquals("dragonfruit", out[19]);
        assertEquals("durian", out[20]);
        assertEquals("elderberry", out[21]);
        assertEquals("feijoa", out[22]);
        assertEquals("fig", out[23]);
        assertEquals("gooseberry", out[24]);
        assertEquals("grape", out[25]);
        assertEquals("grapefruit", out[26]);
        assertEquals("guava", out[27]);
        assertEquals("honeydew", out[28]);
        assertEquals("huckleberry", out[29]);
        assertEquals("jabuticaba", out[30]);
        assertEquals("jackfruit", out[31]);
        assertEquals("jambul", out[32]);
        assertEquals("jujube", out[33]);
        assertEquals("juniper", out[34]);
        assertEquals("kiwifruit", out[35]);
        assertEquals("kumquat", out[36]);
        assertEquals("lemon", out[37]);
        assertEquals("lime", out[38]);
        assertEquals("longan", out[39]);
        assertEquals("loquat", out[40]);
        assertEquals("lychee", out[41]);
        assertEquals("mandarine", out[42]);
        assertEquals("mango", out[43]);
        assertEquals("marionberry", out[44]);
        assertEquals("melon", out[45]);
        assertEquals("miracle", out[46]);
        assertEquals("mulberry", out[47]);
        assertEquals("nance", out[48]);
        assertEquals("nectarine", out[49]);
        assertEquals("olive", out[50]);
        assertEquals("orange", out[51]);
        assertEquals("papaya", out[52]);
        assertEquals("passionfruit", out[53]);
        assertEquals("peach", out[54]);
        assertEquals("pear", out[55]);
        assertEquals("persimmon", out[56]);
        assertEquals("physalis", out[57]);
        assertEquals("pineapple", out[58]);
        assertEquals("plantain", out[59]);
        assertEquals("plum", out[60]);
        assertEquals("plumcot", out[61]);
        assertEquals("pomegranate", out[62]);
        assertEquals("pomelo", out[63]);
        assertEquals("quince", out[64]);
        assertEquals("raisin", out[65]);
        assertEquals("rambutan", out[66]);
        assertEquals("raspberry", out[67]);
        assertEquals("redcurrant", out[68]);
        assertEquals("salak", out[69]);
        assertEquals("salmonberry", out[70]);
        assertEquals("satsuma", out[71]);
        assertEquals("strawberry", out[72]);
        assertEquals("tamarillo", out[73]);
        assertEquals("tamarind", out[74]);
        assertEquals("tangerine", out[75]);
        assertEquals("tomato", out[76]);
        assertEquals("watermelon", out[77]);
        assertEquals("yuzu", out[78]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("got method", out[0]);
        assertEquals("arg", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMethodBindsThis() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "method_binds_this.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(1, out.length);
        assertEquals("foo1", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testOnInstance() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "on_instance.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(4, out.length);
        assertEquals("bar value", out[0]);
        assertEquals("baz value", out[1]);
        assertEquals("bar value", out[2]);
        assertEquals("baz value", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnBool() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "set_on_bool.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have fields."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testSetOnClass() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "set_on_class.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have fields."));
        assertThat(err[1], containsString("[line 2]"));
    }

    @Test
    public void testSetOnFunction() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "set_on_function.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have fields."));
        assertThat(err[1], containsString("[line 3]"));
    }

    @Test
    public void testSetOnNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "set_on_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have fields."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testSetOnNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "set_on_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have fields."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testSetOnString() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "set_on_string.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Only instances have fields."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testUndefined() {
        Path file = Paths.get("src", "test", "resources", "programs", "field", "undefined.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined property 'bar'."));
        assertThat(err[1], containsString("[line 4]"));
    }
}
