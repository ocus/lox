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
public class InterpreterVariableTest {

    @Test
    public void testUndefinedGlobal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/undefined_global.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseThisAsVar() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/use_this_as_var.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInNestedBlock() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/in_nested_block.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("outer", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDuplicateLocal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/duplicate_local.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUndefinedLocal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/undefined_local.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInMiddleOfBlock() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/in_middle_of_block.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("a", out[0]);
        assertEquals("a b", out[1]);
        assertEquals("a c", out[2]);
        assertEquals("a b d", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUnreachedUndefined() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/unreached_undefined.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseFalseAsVar() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/use_false_as_var.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testDuplicateParameter() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/duplicate_parameter.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowAndLocal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/shadow_and_local.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("outer", out[0]);
        assertEquals("inner", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testRedeclareGlobal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/redeclare_global.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testRedefineGlobal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/redefine_global.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("2", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowGlobal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/shadow_global.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("shadow", out[0]);
        assertEquals("global", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseNilAsVar() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/use_nil_as_var.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseLocalInInitializer() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/use_local_in_initializer.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseGlobalInInitializer() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/use_global_in_initializer.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("value", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testScopeReuseInDifferentBlocks() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/scope_reuse_in_different_blocks.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("first", out[0]);
        assertEquals("second", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowLocal() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/shadow_local.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("shadow", out[0]);
        assertEquals("local", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testCollideWithParameter() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/collide_with_parameter.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testLocalFromMethod() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/local_from_method.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("variable", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testEarlyBound() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/early_bound.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("outer", out[0]);
        assertEquals("outer", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUninitialized() {
        InterpreterTestHelper helper = new InterpreterTestHelper("src/test/resources/programs/variable/uninitialized.lox");
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }
}
