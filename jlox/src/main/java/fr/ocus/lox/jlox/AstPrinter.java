package fr.ocus.lox.jlox;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
// Creates an unambiguous, if ugly, string representation of AST nodes.
public class AstPrinter implements Expr.Visitor<String>, Stmt.Visitor<String> {
    String print(Stmt stmt) {
        return stmt.accept(this);
    }

    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitArrayExpr(Expr.Array expr) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return parenthesize(expr.name.lexeme, expr.value);
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitSetExpr(Expr.Set expr) {
        StringBuilder builder = new StringBuilder();

        builder.append("(set ");
        builder.append(expr.object.accept(this));
        builder.append(" ");
        builder.append(expr.name.lexeme);
        builder.append(" ");
        builder.append(expr.value.accept(this));
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitThisExpr(Expr.This expr) {
        return expr.keyword.lexeme;
    }

    @Override
    public String visitSuperExpr(Expr.Super expr) {
        niy(expr);
        return null;
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        return parenthesize("call " + expr.callee.accept(this), expr.arguments.toArray(new Expr[]{}));
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        StringBuilder builder = new StringBuilder();

        builder.append("(get ");
        builder.append(expr.object.accept(this));
        builder.append(" ");
        builder.append(expr.name.lexeme);
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        if (expr.value instanceof String) return "\"" + expr.value + "\"";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name.lexeme;
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        return parenthesize("block", stmt.statements.toArray(new Stmt[]{}));
    }

    @Override
    public String visitClassStmt(Stmt.Class stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("(class ");
        builder.append(stmt.name.lexeme);
        if (stmt.superclass != null) {
            builder.append(" ");
            builder.append("<<");
            builder.append(stmt.superclass.accept(this));
        }
        if (!stmt.methods.isEmpty()) {
            builder.append(" ");
            builder.append(parenthesize("methods", stmt.methods.toArray(new Stmt[]{})));
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt) {
        return parenthesize("expression", stmt.expression);
    }

    @Override
    public String visitFunctionStmt(Stmt.Function stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("(function ");
        builder.append(stmt.name.lexeme);
        if (!stmt.parameters.isEmpty()) {
            builder.append(" ");
            builder.append(parenthesize("parameters", stmt.parameters.toArray(new Token[]{})));
        }
        if (!stmt.body.isEmpty()) {
            builder.append(" ");
            builder.append(parenthesize("body", stmt.body.toArray(new Stmt[]{})));
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("(if ");
        builder.append(parenthesize("condition", stmt.condition));
        builder.append(" ");
        builder.append(parenthesize("then", stmt.thenBranch));
        if (stmt.elseBranch != null) {
            builder.append(" ");
            builder.append(parenthesize("else", stmt.thenBranch));
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitPrintStmt(Stmt.Print stmt) {
        return parenthesize("print", stmt.expression);
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt) {
        StringBuilder builder = new StringBuilder();
        builder.append("(var ");
        builder.append(stmt.name.lexeme);
        if (stmt.initializer != null) {
            builder.append(" ");
            builder.append(stmt.initializer.accept(this));
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String visitReturnStmt(Stmt.Return stmt) {
        return parenthesize("return", stmt.value);
    }

    @Override
    public String visitWhileStmt(Stmt.While stmt) {
        StringBuilder builder = new StringBuilder();

        builder.append("(while ");
        builder.append(parenthesize("condition", stmt.condition));
        builder.append(" ");
        builder.append(parenthesize("body", stmt.body));
        builder.append(")");

        return builder.toString();
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    private String parenthesize(String name, Stmt... stmts) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Stmt stmt : stmts) {
            builder.append(" ");
            builder.append(stmt.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    private String parenthesize(String name, Token... tokens) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Token token : tokens) {
            builder.append(" ");
            builder.append(token.lexeme);
        }
        builder.append(")");

        return builder.toString();
    }

    private void niy(Expr expr) {
        throw new RuntimeException("Expr: not implemented yet " + expr + ".");
    }

    private void niy(Stmt stmt) {
        throw new RuntimeException("Stmt: not implemented yet " + stmt.getClass().getName() + ".");
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox-ast [script]");
        } else if (args.length == 1) {
            String path = args[0];
            Scanner scanner = new Scanner(System.err, new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset()));
            List<Token> tokens = scanner.scanTokens();

            Parser parser = new Parser(System.err, tokens);
            List<Stmt> statements = parser.parse();
            for (Stmt stmt : statements) {
                System.out.println(new AstPrinter().print(stmt));
            }
        }
    }

}
