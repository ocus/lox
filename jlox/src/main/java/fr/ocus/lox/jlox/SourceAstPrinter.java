package fr.ocus.lox.jlox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-04
 */
public class SourceAstPrinter implements Stmt.Visitor<String>, Expr.Visitor<String> {
    List<String> print(List<Stmt> stmts) {
        List<String> prints = new ArrayList<>(stmts.size());
        for (Stmt stmt : stmts) {
            prints.add(print(stmt));
        }
        return prints;
    }

    String print(Stmt stmt) {
        return stmt.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(expr.name.lexeme);
        builder.append(" = ");
        builder.append(expr.value.accept(this));
        builder.append(";\n");

        return builder.toString();
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(expr.left.accept(this)).append(" ");
        builder.append(expr.operator.lexeme).append(" ");
        builder.append(expr.right.accept(this));

        return builder.toString();
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(expr.callee.accept(this)).append("(");
        for (int i = 0; i < expr.arguments.size(); i++) {
            Expr argExpr = expr.arguments.get(i);
            builder.append(argExpr.accept(this));
            if (i < expr.arguments.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        StringBuilder builder = new StringBuilder();

        builder.append("(");
        builder.append(expr.expression.accept(this));
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value instanceof String) {
            return "\"" + expr.value + "\"";
        }
        return String.valueOf(expr.value);
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        StringBuilder builder = new StringBuilder();

        builder.append(expr.left.accept(this)).append(" ");
        builder.append(expr.operator.lexeme).append(" ");
        builder.append(expr.right.accept(this));

        return builder.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return "[EXPR-UNARY]";
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name.lexeme;
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        return "{\n" + statementList(stmt.statements) + "}\n";
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt) {
        return stmt.expression.accept(this);
    }

    @Override
    public String visitFunctionStmt(Stmt.Function stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("fun ").append(stmt.name.lexeme);
        builder.append("(");
        for (int i = 0; i < stmt.parameters.size(); i++) {
            Token token = stmt.parameters.get(i);
            builder.append(token.lexeme);
            if (i < stmt.parameters.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(") {\n");
        builder.append(statementList(stmt.body));

        builder.append("}\n");

        return builder.toString();
    }

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("if (").append(stmt.condition.accept(this)).append(") ");
        builder.append(stmt.thenBranch.accept(this));
        if (stmt.elseBranch != null) {
            builder.append("else ");
            builder.append(stmt.elseBranch.accept(this));
        }

        return builder.toString();
    }

    @Override
    public String visitPrintStmt(Stmt.Print stmt) {
        return "print " + stmt.expression.accept(this) + ";\n";
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("var ").append(stmt.name.lexeme);
        if (stmt.initializer != null) {
            builder.append(" = ").append(stmt.initializer.accept(this));
        }
        builder.append(";\n");

        return builder.toString();
    }

    @Override
    public String visitReturnStmt(Stmt.Return stmt) {
        return "return " + stmt.value.accept(this) + ";\n";
    }

    @Override
    public String visitWhileStmt(Stmt.While stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("while (").append(stmt.condition.accept(this)).append(") ");
        builder.append(stmt.body.accept(this));

        return builder.toString();
    }

    private String statementList(List<Stmt> stmts) {
        StringBuilder builder = new StringBuilder();
        for (Stmt stmt : stmts) {
            builder.append(stmt.accept(this));
        }
        return builder.toString();
    }

    private static String programToString(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            Scanner scanner = new Scanner(System.err, new String(bytes));
            List<Token> tokens = scanner.scanTokens();

            Parser parser = new Parser(System.err, tokens);
            List<Stmt> statements = parser.parse();
            SourceAstPrinter printer = new SourceAstPrinter();
            return Arrays.stream(printer.print(statements).toArray(new String[0]))
                .collect(Collectors.joining(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static public void main(String[] args) {
        String[] programs = new String[]{
            "programs/scope.lox",
            "programs/fibonacci.lox",
            "src/test/resources/programs/closure/assign_to_closure.lox",
            "src/test/resources/programs/closure/nested_closure.lox",
            "src/test/resources/programs/for/closure_in_body.lox",
            "src/test/resources/programs/logical_operator/and.lox",
        };
        for (String program : programs) {
            System.out.println("// ++++++++++++++++++++++++++++++++++++++++");
            System.out.println("// " + program);
            System.out.println(programToString(program));
        }
    }
}
