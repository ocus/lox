package fr.ocus.lox.jlox;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class InterpreterOperatorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterpreterOperatorTest.class.getName());

    @Test
    public void testAdd() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("579", out[0]);
        assertEquals("string", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testAddBoolNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add_bool_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be two numbers or two strings."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testAddBoolNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add_bool_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be two numbers or two strings."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testAddBoolString() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add_bool_string.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be two numbers or two strings."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testAddNilNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add_nil_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be two numbers or two strings."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testAddNumNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add_num_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be two numbers or two strings."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testAddStringNil() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "add_string_nil.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be two numbers or two strings."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testComparison() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "comparison.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(20, out.length);
        assertEquals("true", out[0]);
        assertEquals("false", out[1]);
        assertEquals("false", out[2]);
        assertEquals("true", out[3]);
        assertEquals("true", out[4]);
        assertEquals("false", out[5]);
        assertEquals("false", out[6]);
        assertEquals("false", out[7]);
        assertEquals("true", out[8]);
        assertEquals("false", out[9]);
        assertEquals("true", out[10]);
        assertEquals("true", out[11]);
        assertEquals("false", out[12]);
        assertEquals("false", out[13]);
        assertEquals("false", out[14]);
        assertEquals("false", out[15]);
        assertEquals("true", out[16]);
        assertEquals("true", out[17]);
        assertEquals("true", out[18]);
        assertEquals("true", out[19]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDivide() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "divide.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("4", out[0]);
        assertEquals("1", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDivideNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "divide_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testDivideNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "divide_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testEquals() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "equals.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(10, out.length);
        assertEquals("true", out[0]);
        assertEquals("true", out[1]);
        assertEquals("false", out[2]);
        assertEquals("true", out[3]);
        assertEquals("false", out[4]);
        assertEquals("true", out[5]);
        assertEquals("false", out[6]);
        assertEquals("false", out[7]);
        assertEquals("false", out[8]);
        assertEquals("false", out[9]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testEqualsClass() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "equals_class.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("true", out[0]);
        assertEquals("false", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testGreaterNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "greater_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testGreaterNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "greater_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testGreaterOrEqualNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "greater_or_equal_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testGreaterOrEqualNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "greater_or_equal_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testLessNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "less_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testLessNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "less_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testLessOrEqualNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "less_or_equal_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testLessOrEqualNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "less_or_equal_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testMultiply() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "multiply.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("15", out[0]);
        assertEquals("3.702", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testMultiplyNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "multiply_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testMultiplyNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "multiply_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testNegate() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "negate.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(3, out.length);
        assertEquals("-3", out[0]);
        assertEquals("3", out[1]);
        assertEquals("-3", out[2]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNegateNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "negate_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operand must be a number."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testNot() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "not.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(8, out.length);
        assertEquals("false", out[0]);
        assertEquals("true", out[1]);
        assertEquals("true", out[2]);
        assertEquals("false", out[3]);
        assertEquals("false", out[4]);
        assertEquals("true", out[5]);
        assertEquals("false", out[6]);
        assertEquals("false", out[7]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNotClass() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "not_class.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("false", out[0]);
        assertEquals("false", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testNotEquals() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "not_equals.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(10, out.length);
        assertEquals("false", out[0]);
        assertEquals("false", out[1]);
        assertEquals("true", out[2]);
        assertEquals("false", out[3]);
        assertEquals("true", out[4]);
        assertEquals("false", out[5]);
        assertEquals("true", out[6]);
        assertEquals("true", out[7]);
        assertEquals("true", out[8]);
        assertEquals("true", out[9]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSubtract() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "subtract.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertEquals(2, out.length);
        assertEquals("1", out[0]);
        assertEquals("0", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testSubtractNonnumNum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "subtract_nonnum_num.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testSubtractNumNonnum() {
        Path file = Paths.get("src", "test", "resources", "programs", "operator", "subtract_num_nonnum.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        LOGGER.debug("{} :: OUT :: {}", file, Arrays.toString(out));
        LOGGER.debug("{} :: ERR :: {}", file, Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Operands must be numbers."));
        assertThat(err[1], containsString("[line 1]"));
    }
}
