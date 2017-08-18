package fr.ocus.lox.jlox;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
public class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void> {
    final Environment globals = new Environment();
    private Environment environment = globals;
    private final Map<Expr, Integer> locals = new HashMap<>();
    private final PrintStream printStream;
    private final PrintStream errorStream;

    Interpreter() {
        this(System.out, System.err);
    }

    Interpreter(PrintStream printStream, PrintStream errorStream) {
        this.printStream = printStream;
        this.errorStream = errorStream;
        globals.define("clock", new LoxCallable() {
            @Override
            public String toString() {
                return "<fn:lox clock>";
            }

            @Override
            public int arity() {
                return 0;
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                return (double) System.currentTimeMillis() / 1000.0;
            }
        });
        globals.define("dump_env", new LoxCallable() {
            @Override
            public String toString() {
                return "<fn:lox dump_env>";
            }

            @Override
            public int arity() {
                return 0;
            }

            @Override
            public Object call(Interpreter interpreter, List<Object> arguments) {
                Map<String, Object> values = interpreter.environment.getAllValues();
                print("Env dump start: (", values.size(), ")\n");
                for (String name : values.keySet()) {
                    print("- ", name, " = ", values.get(name), "\n");
                }
                print("Env dump end\n");
                return null;
            }
        });
    }

    void interpret(List<Stmt> statements) {
        try {
            for (Stmt statement : statements) {
                execute(statement);
            }
        } catch (StackOverflowError error) {
            JLox.runtimeError(errorStream, new RuntimeError(new Token(TokenType.EOF, "", "", -1), "Stack overflow."));
        } catch (RuntimeError error) {
            JLox.runtimeError(errorStream, error);
        }
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case MINUS:
                checkNumberOperands(expr.operator, left, right);
                return (double) left - (double) right;
            case PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }

                if (left instanceof String && right instanceof String) {
                    return (String) left + right;
                }

                throw new RuntimeError(expr.operator, "Operands must be two numbers or two strings.");
            case SLASH:
                checkNumberOperands(expr.operator, left, right);
                checkNotZeroOperand(expr.operator, right);
                return (double) left / (double) right;
            case STAR:
                checkNumberOperands(expr.operator, left, right);
                return (double) left * (double) right;
            case GREATER:
                checkNumberOperands(expr.operator, left, right);
                return (double) left > (double) right;
            case GREATER_EQUAL:
                checkNumberOperands(expr.operator, left, right);
                return (double) left >= (double) right;
            case LESS:
                checkNumberOperands(expr.operator, left, right);
                return (double) left < (double) right;
            case LESS_EQUAL:
                checkNumberOperands(expr.operator, left, right);
                return (double) left <= (double) right;
            case BANG_EQUAL:
                return !isEqual(left, right);
            case EQUAL_EQUAL:
                return isEqual(left, right);
        }

        // Unreachable.
        return null;
    }

    @Override
    public Void visitFunctionStmt(Stmt.Function stmt) {
        LoxFunction function = new LoxFunction(stmt, environment, false);
        environment.define(stmt.name.lexeme, function);
        return null;
    }

    @Override
    public Void visitReturnStmt(Stmt.Return stmt) {
        Object value = null;
        if (stmt.value != null) value = evaluate(stmt.value);

        throw new Return(value);
    }

    @Override
    public Object visitCallExpr(Expr.Call expr) {
        Object callee = evaluate(expr.callee);

        List<Object> arguments = new ArrayList<>();
        for (Expr argument : expr.arguments) {
            arguments.add(evaluate(argument));
        }

        if (!(callee instanceof LoxCallable)) {
            throw new RuntimeError(expr.paren, "Can only call functions and classes.");
        }

        LoxCallable function = (LoxCallable) callee;
        if (arguments.size() != function.arity()) {
            throw new RuntimeError(expr.paren, "Expected " + function.arity() + " arguments but got " + arguments.size() + ".");
        }
        return function.call(this, arguments);
    }

    @Override
    public Object visitGetExpr(Expr.Get expr) {
        Object object = evaluate(expr.object);
        if (object instanceof LoxInstance) {
            return ((LoxInstance) object).getProperty(expr.name);
        }

        throw new RuntimeError(expr.name, "Only instances have properties.");
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitIndexGetExpr(Expr.IndexGet expr) {
        Object index = expr.index.accept(this);
        if (!(index instanceof Double)) {
            throw new RuntimeException(); // not a number
        }
        double doubleIndex = (double) index;
        if ((doubleIndex != Math.floor(doubleIndex)) || Double.isInfinite(doubleIndex)) {
            throw new RuntimeException(); // not a whole number
        }
        Object object = expr.object.accept(this);
        if (object instanceof List) {
            return ((List) object).get((int) doubleIndex);
        }
        else if (object instanceof String) {
            return ((String) object).charAt((int) doubleIndex);
        }
        throw new RuntimeException(); // not a list
    }

    @Override
    public Object visitIndexSetExpr(Expr.IndexSet expr) {
        Object index = expr.index.accept(this);
        if (!(index instanceof Double)) {
            throw new RuntimeException(); // not a number
        }
        double doubleIndex = (double) index;
        if ((doubleIndex != Math.floor(doubleIndex)) || Double.isInfinite(doubleIndex)) {
            throw new RuntimeException(); // not a whole number
        }
        Object object = expr.object.accept(this);
        if (object instanceof List) {
            ((List) object).set((int) doubleIndex, expr.value.accept(this));
        }
        else if (object instanceof String) {
            throw new RuntimeException(); // hmmm strings are immutable
        }
        else {
            throw new RuntimeException(); // not a list nor a string
        }
        return null;
    }

    @Override
    public Object visitLogicalExpr(Expr.Logical expr) {
        Object left = evaluate(expr.left);

        if (expr.operator.type == TokenType.OR) {
            if (isTruthy(left)) return left;
        } else {
            if (!isTruthy(left)) return left;
        }

        return evaluate(expr.right);
    }

    @Override
    public Object visitSetExpr(Expr.Set expr) {
        Object value = evaluate(expr.value);
        Object object = evaluate(expr.object);

        if (object instanceof LoxInstance) {
            ((LoxInstance) object).fields.put(expr.name.lexeme, value);
            return value;
        }

        throw new RuntimeError(expr.name, "Only instances have fields.");
    }

    @Override
    public Object visitThisExpr(Expr.This expr) {
        return environment.get(expr.keyword);
    }

    @Override
    public Object visitSuperExpr(Expr.Super expr) {
        int distance = locals.get(expr);
        LoxClass superclass = (LoxClass) environment.getAt(distance, "super");
        LoxInstance receiver = (LoxInstance) environment.getAt(distance - 1, "this");
        LoxFunction method = superclass.findMethod(receiver, expr.method.lexeme);
        if (method == null) {
            throw new RuntimeError(expr.method, "Undefined property '" + expr.method.lexeme + "'.");
        }
        return method;
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case BANG:
                return !isTruthy(right);
            case MINUS:
                checkNumberOperand(expr.operator, right);
                return -(double) right;
        }

        // Unreachable.
        return null;
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return lookUpVariable(expr.name, expr);
    }

    @Override
    public Object visitArrayExpr(Expr.Array expr) {
        List<Object> array = new ArrayList<>(expr.elements.size());
        for (Expr element : expr.elements) {
            array.add(element.accept(this));
        }
        return array;
    }

    @Override
    public Object visitAssignExpr(Expr.Assign expr) {
        Object value = evaluate(expr.value);

        Integer distance = locals.get(expr);
        if (distance != null) {
            environment.assignAt(distance, expr.name, value);
        } else {
            environment.assign(expr.name, value);
        }
        return value;
    }

    @Override
    public Void visitExpressionStmt(Stmt.Expression stmt) {
        evaluate(stmt.expression);
        return null;
    }

    @Override
    public Void visitIfStmt(Stmt.If stmt) {
        if (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.thenBranch);
        } else if (stmt.elseBranch != null) {
            execute(stmt.elseBranch);
        }
        return null;
    }

    @Override
    public Void visitPrintStmt(Stmt.Print stmt) {
        Object value = evaluate(stmt.expression);
        print(value, "\n"); // platform-independent newline
        return null;
    }

    private void print(Object... values) {
        for (Object value : values) {
            printStream.print(stringify(value));
        }
    }

    @Override
    public Void visitWhileStmt(Stmt.While stmt) {
        while (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.body);
        }
        return null;
    }

    @Override
    public Void visitVarStmt(Stmt.Var stmt) {
        Object value = null;
        if (stmt.initializer != null) {
            value = evaluate(stmt.initializer);
        }

        environment.define(stmt.name.lexeme, value);
        return null;
    }

    @Override
    public Void visitBlockStmt(Stmt.Block stmt) {
        executeBlock(stmt.statements, new Environment(environment));
        return null;
    }

    @Override
    public Void visitClassStmt(Stmt.Class stmt) {
        environment.define(stmt.name.lexeme, null);

        Object superclass = null;
        if (stmt.superclass != null) {
            superclass = evaluate(stmt.superclass);
            if (!(superclass instanceof LoxClass)) {
                throw new RuntimeError(stmt.name, "Superclass must be a class.");
            }

            environment = new Environment(environment);
            environment.define("super", superclass);
        }
        Map<String, LoxFunction> methods = new HashMap<>(stmt.methods.size());
        for (Stmt.Function method : stmt.methods) {
            LoxFunction function = new LoxFunction(method, environment, method.name.lexeme.equals("init"));
            methods.put(method.name.lexeme, function);
        }
        LoxClass cls = new LoxClass(stmt.name.lexeme, (LoxClass) superclass, methods);
        if (superclass != null) {
            environment = environment.enclosing;
        }

        environment.assign(stmt.name, cls);
        return null;
    }

    private void execute(Stmt stmt) {
        stmt.accept(this);
    }

    void executeBlock(List<Stmt> statements, Environment environment) {
        Environment previous = this.environment;
        try {
            this.environment = environment;

            for (Stmt statement : statements) {
                execute(statement);
            }
        } finally {
            this.environment = previous;
        }
    }

    void resolve(Expr expr, int depth) {
        locals.put(expr, depth);
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    private Object lookUpVariable(Token name, Expr expr) {
        Integer distance = locals.get(expr);
        if (distance != null) {
            return environment.getAt(distance, name.lexeme);
        }
        return globals.get(name);
    }

    private void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Double) return;
        throw new RuntimeError(operator, "Operand must be a number.");
    }

    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double) return;
        throw new RuntimeError(operator, "Operands must be numbers.");
    }

    private void checkNotZeroOperand(Token operator, Object operand) {
        if ((double) operand != 0) return;
        throw new RuntimeError(operator, "Division by zero.");
    }

    private boolean isTruthy(Object object) {
        return object != null && (!(object instanceof Boolean) || (boolean) object);
    }

    private boolean isEqual(Object a, Object b) {
        // nil is only equal to nil.
        return a == null && b == null || a != null && a.equals(b);
    }

    private String stringify(Object object) {
        if (object == null) return "nil";

        // Hack. Work around Java adding ".0" to integer-valued doubles.
        if (object instanceof Double) {
            String text = object.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }

        return object.toString();
    }

    private void niy(Expr expr) {
        throw new RuntimeException("Expr: not implemented yet " + expr.getClass().getName() + ".");
    }

    private void niy(Stmt stmt) {
        throw new RuntimeException("Stmt: not implemented yet " + stmt.getClass().getName() + ".");
    }
}
