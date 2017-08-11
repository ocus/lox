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
public class InterpreterVariableTest {

    @Test
    public void testCollideWithParameter() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "collide_with_parameter.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'a': Variable with this name already declared in this scope."));
    }

    @Test
    public void testDuplicateLocal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "duplicate_local.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'a': Variable with this name already declared in this scope."));
    }

    @Test
    public void testDuplicateParameter() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "duplicate_parameter.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'arg': Variable with this name already declared in this scope."));
    }

    @Test
    public void testEarlyBound() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "early_bound.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("outer", out[0]);
        assertEquals("outer", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInMiddleOfBlock() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "in_middle_of_block.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("a", out[0]);
        assertEquals("a b", out[1]);
        assertEquals("a c", out[2]);
        assertEquals("a b d", out[3]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testInNestedBlock() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "in_nested_block.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("outer", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testLocalFromMethod() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "local_from_method.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("variable", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testRedeclareGlobal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "redeclare_global.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testRedefineGlobal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "redefine_global.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("2", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testScopeReuseInDifferentBlocks() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "scope_reuse_in_different_blocks.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("first", out[0]);
        assertEquals("second", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowAndLocal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "shadow_and_local.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("outer", out[0]);
        assertEquals("inner", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowGlobal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "shadow_global.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("shadow", out[0]);
        assertEquals("global", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testShadowLocal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "shadow_local.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("shadow", out[0]);
        assertEquals("local", out[1]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUndefinedGlobal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "undefined_global.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined variable 'notDefined'."));
        assertThat(err[1], containsString("[line 1]"));
    }

    @Test
    public void testUndefinedLocal() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "undefined_local.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(2, err.length);
        assertThat(err[0], containsString("Undefined variable 'notDefined'."));
        assertThat(err[1], containsString("[line 2]"));
    }

    @Test
    public void testUninitialized() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "uninitialized.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("nil", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUnreachedUndefined() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "unreached_undefined.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("ok", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseFalseAsVar() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "use_false_as_var.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'false': Expect variable name."));
    }

    @Test
    public void testUseGlobalInInitializer() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "use_global_in_initializer.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertEquals("value", out[0]);
        assertArrayEquals(new String[]{""}, err);
    }

    @Test
    public void testUseLocalInInitializer() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "use_local_in_initializer.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'a': Cannot read local variable in its own initializer."));
    }

    @Test
    public void testUseNilAsVar() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "use_nil_as_var.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'nil': Expect variable name."));
    }

    @Test
    public void testUseThisAsVar() {
        Path file = Paths.get("src", "test", "resources", "programs", "variable", "use_this_as_var.lox");
        InterpreterTestHelper helper = new InterpreterTestHelper(file);
        helper.run();
        String[] out = helper.getOutput();
        String[] err = helper.getError();
        System.err.println(file + " :: OUT: " + Arrays.toString(out));
        System.err.println(file + " :: ERR: " + Arrays.toString(err));
        assertArrayEquals(new String[]{""}, out);
        assertEquals(1, err.length);
        assertThat(err[0], containsString("Error at 'this': Expect variable name."));
    }
}
