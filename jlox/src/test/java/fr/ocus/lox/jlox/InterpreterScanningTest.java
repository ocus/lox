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
public class InterpreterScanningTest {

    @Test
    public void testIdentifiers() {
        Path file = Paths.get("src", "test", "resources", "programs", "scanning", "identifiers.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(9, out.length);
        assertEquals("IDENTIFIER andy null", out[0]);
        assertEquals("IDENTIFIER formless null", out[1]);
        assertEquals("IDENTIFIER fo null", out[2]);
        assertEquals("IDENTIFIER _ null", out[3]);
        assertEquals("IDENTIFIER _123 null", out[4]);
        assertEquals("IDENTIFIER _abc null", out[5]);
        assertEquals("IDENTIFIER ab123 null", out[6]);
        assertEquals("IDENTIFIER abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_ null", out[7]);
        assertEquals("EOF  null", out[8]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testKeywords() {
        Path file = Paths.get("src", "test", "resources", "programs", "scanning", "keywords.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(16, out.length);
        assertEquals("AND and null", out[0]);
        assertEquals("CLASS class null", out[1]);
        assertEquals("ELSE else null", out[2]);
        assertEquals("FALSE false null", out[3]);
        assertEquals("FOR for null", out[4]);
        assertEquals("FUN fun null", out[5]);
        assertEquals("IF if null", out[6]);
        assertEquals("NIL nil null", out[7]);
        assertEquals("OR or null", out[8]);
        assertEquals("RETURN return null", out[9]);
        assertEquals("SUPER super null", out[10]);
        assertEquals("THIS this null", out[11]);
        assertEquals("TRUE true null", out[12]);
        assertEquals("VAR var null", out[13]);
        assertEquals("WHILE while null", out[14]);
        assertEquals("EOF  null", out[15]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNumbers() {
        Path file = Paths.get("src", "test", "resources", "programs", "scanning", "numbers.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(7, out.length);
        assertEquals("NUMBER 123 123.0", out[0]);
        assertEquals("NUMBER 123.456 123.456", out[1]);
        assertEquals("DOT . null", out[2]);
        assertEquals("NUMBER 456 456.0", out[3]);
        assertEquals("NUMBER 123 123.0", out[4]);
        assertEquals("DOT . null", out[5]);
        assertEquals("EOF  null", out[6]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testPunctuators() {
        Path file = Paths.get("src", "test", "resources", "programs", "scanning", "punctuators.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(19, out.length);
        assertEquals("LEFT_PAREN ( null", out[0]);
        assertEquals("RIGHT_PAREN ) null", out[1]);
        assertEquals("LEFT_BRACE { null", out[2]);
        assertEquals("RIGHT_BRACE } null", out[3]);
        assertEquals("SEMICOLON ; null", out[4]);
        assertEquals("COMMA , null", out[5]);
        assertEquals("PLUS + null", out[6]);
        assertEquals("MINUS - null", out[7]);
        assertEquals("STAR * null", out[8]);
        assertEquals("BANG_EQUAL != null", out[9]);
        assertEquals("EQUAL_EQUAL == null", out[10]);
        assertEquals("LESS_EQUAL <= null", out[11]);
        assertEquals("GREATER_EQUAL >= null", out[12]);
        assertEquals("BANG_EQUAL != null", out[13]);
        assertEquals("LESS < null", out[14]);
        assertEquals("GREATER > null", out[15]);
        assertEquals("SLASH / null", out[16]);
        assertEquals("DOT . null", out[17]);
        assertEquals("EOF  null", out[18]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testStrings() {
        Path file = Paths.get("src", "test", "resources", "programs", "scanning", "strings.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(3, out.length);
        assertEquals("STRING \"\" ", out[0]);
        assertEquals("STRING \"string\" string", out[1]);
        assertEquals("EOF  null", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testWhitespace() {
        Path file = Paths.get("src", "test", "resources", "programs", "scanning", "whitespace.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals(5, out.length);
        assertEquals("IDENTIFIER space null", out[0]);
        assertEquals("IDENTIFIER tabs null", out[1]);
        assertEquals("IDENTIFIER newlines null", out[2]);
        assertEquals("IDENTIFIER end null", out[3]);
        assertEquals("EOF  null", out[4]);
        assertArrayEquals(new String[]{""}, err);
    }
}
