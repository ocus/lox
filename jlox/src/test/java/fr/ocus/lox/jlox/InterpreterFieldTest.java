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
public class InterpreterFieldTest {

    @Test
    public void testMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("got method", out[0]);
        assertEquals("arg", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMany() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/many.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
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
    public void testGetOnNil() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/get_on_nil.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnBool() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/set_on_bool.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallNonfunctionField() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/call_nonfunction_field.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGetOnString() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/get_on_string.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnString() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/set_on_string.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUndefined() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/undefined.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnNil() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/set_on_nil.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnClass() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/set_on_class.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnNum() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/set_on_num.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGetOnNum() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/get_on_num.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCallFunctionField() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/call_function_field.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("bar", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGetOnBool() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/get_on_bool.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGetOnClass() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/get_on_class.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGetOnFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/get_on_function.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMethodBindsThis() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/method_binds_this.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("foo1", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSetOnFunction() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/set_on_function.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testOnInstance() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/field/on_instance.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("bar value", out[0]);
        assertEquals("baz value", out[1]);
        assertEquals("bar value", out[2]);
        assertEquals("baz value", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }
}
