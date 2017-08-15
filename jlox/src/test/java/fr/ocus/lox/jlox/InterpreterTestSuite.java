package fr.ocus.lox.jlox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InterpreterTest.class,
        InterpreterBoolTest.class,
        InterpreterStringTest.class,
        InterpreterScanningTest.class,
        InterpreterForTest.class,
        InterpreterInheritanceTest.class,
        InterpreterWhileTest.class,
        InterpreterOperatorTest.class,
        InterpreterNilTest.class,
        InterpreterNumberTest.class,
        InterpreterLogicalOperatorTest.class,
        InterpreterFunctionTest.class,
        InterpreterLimitTest.class,
        InterpreterBlockTest.class,
        InterpreterClassTest.class,
        InterpreterClosureTest.class,
        InterpreterIfTest.class,
        InterpreterCommentsTest.class,
        InterpreterMethodTest.class,
        InterpreterAssignmentTest.class,
        InterpreterConstructorTest.class,
        InterpreterThisTest.class,
        InterpreterExpressionsTest.class,
        InterpreterBenchmarkTest.class,
        InterpreterCallTest.class,
        InterpreterSuperTest.class,
        InterpreterPrintTest.class,
        InterpreterFieldTest.class,
        InterpreterRegressionTest.class,
        InterpreterVariableTest.class,
        InterpreterReturnTest.class,
})
public class InterpreterTestSuite {
}
