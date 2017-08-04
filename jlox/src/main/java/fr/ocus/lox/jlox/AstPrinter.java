package fr.ocus.lox.jlox;

import java.util.ArrayList;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
// Creates an unambiguous, if ugly, string representation of AST nodes.
public class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
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
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        return parenthesize(expr.callee.accept(this), expr.arguments.toArray(new Expr[0]));
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return "$" + expr.name.lexeme;
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

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
            new Expr.Unary(
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(123)
            ),
            new Token(TokenType.STAR, "*", null, 1),
            new Expr.Grouping(
                new Expr.Literal(45.67)
            )
        );

        System.out.println(new AstPrinter().print(expression));

        expression = new Expr.Binary(
            new Expr.Variable(new Token(TokenType.VAR, "myVar", null, 1)),
            new Token(TokenType.STAR, "/", null, 1),
            new Expr.Binary(
                new Expr.Binary(new Expr.Literal(1), new Token(TokenType.PLUS, "+", null, 1), new Expr.Literal(2)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Binary(new Expr.Literal(3), new Token(TokenType.PLUS, "+", null, 1), new Expr.Literal(4))
            )
        );
        System.out.println(new AstPrinter().print(expression));

        expression = new Expr.Call(
            new Expr.Variable(new Token(TokenType.IDENTIFIER, "myVar", null, 1)),
            new Token(TokenType.RIGHT_PAREN, ")", null, 1),
            new ArrayList<Expr>() {{
                add(new Expr.Binary(
                    new Expr.Binary(new Expr.Literal(1), new Token(TokenType.PLUS, "+", null, 1), new Expr.Literal(2)),
                    new Token(TokenType.STAR, "*", null, 1),
                    new Expr.Binary(new Expr.Literal(3), new Token(TokenType.PLUS, "+", null, 1), new Expr.Literal(4))
                ));
            }}
        );
        System.out.println(new AstPrinter().print(expression));
    }
}
