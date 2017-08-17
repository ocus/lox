package fr.ocus.lox.jlox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InterpreterTest.class,
        InterpreterAssignmentTest.class,
        InterpreterBenchmarkTest.class,
        InterpreterBlockTest.class,
        InterpreterBoolTest.class,
        InterpreterCallTest.class,
        InterpreterClassTest.class,
        InterpreterClosureTest.class,
        InterpreterCommentsTest.class,
        InterpreterConstructorTest.class,
        InterpreterExpressionsTest.class,
        InterpreterFieldTest.class,
        InterpreterForTest.class,
        InterpreterFunctionTest.class,
        InterpreterIfTest.class,
        InterpreterInheritanceTest.class,
        InterpreterLimitTest.class,
        InterpreterLogicalOperatorTest.class,
        InterpreterMethodTest.class,
        InterpreterNilTest.class,
        InterpreterNumberTest.class,
        InterpreterOperatorTest.class,
        InterpreterPrintTest.class,
        InterpreterRegressionTest.class,
        InterpreterReturnTest.class,
        InterpreterScanningTest.class,
        InterpreterStringTest.class,
        InterpreterSuperTest.class,
        InterpreterThisTest.class,
        InterpreterVariableTest.class,
        InterpreterWhileTest.class,
})
public class InterpreterTestSuite {
}
