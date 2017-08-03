package fr.ocus.lox.jlox;

import java.util.ArrayList;
import java.util.List;

import static fr.ocus.lox.jlox.TokenType.BANG;
import static fr.ocus.lox.jlox.TokenType.BANG_EQUAL;
import static fr.ocus.lox.jlox.TokenType.EOF;
import static fr.ocus.lox.jlox.TokenType.EQUAL_EQUAL;
import static fr.ocus.lox.jlox.TokenType.FALSE;
import static fr.ocus.lox.jlox.TokenType.GREATER;
import static fr.ocus.lox.jlox.TokenType.GREATER_EQUAL;
import static fr.ocus.lox.jlox.TokenType.LEFT_PAREN;
import static fr.ocus.lox.jlox.TokenType.LESS;
import static fr.ocus.lox.jlox.TokenType.LESS_EQUAL;
import static fr.ocus.lox.jlox.TokenType.MINUS;
import static fr.ocus.lox.jlox.TokenType.NIL;
import static fr.ocus.lox.jlox.TokenType.NUMBER;
import static fr.ocus.lox.jlox.TokenType.PLUS;
import static fr.ocus.lox.jlox.TokenType.PRINT;
import static fr.ocus.lox.jlox.TokenType.RIGHT_PAREN;
import static fr.ocus.lox.jlox.TokenType.SEMICOLON;
import static fr.ocus.lox.jlox.TokenType.SLASH;
import static fr.ocus.lox.jlox.TokenType.STAR;
import static fr.ocus.lox.jlox.TokenType.STRING;
import static fr.ocus.lox.jlox.TokenType.TRUE;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
//
// Grammar:
//    program        → statement* EOF ;
//    statement      → exprStmt
//                   | printStmt ;
//    exprStmt       → expression ";" ;
//    printStmt      → "print" expression ";" ;
//    expression     → equality ;
//    equality       → comparison ( ( "!=" | "==" ) comparison )* ;
//    comparison     → addition ( ( ">" | ">=" | "<" | "<=" ) addition )* ;
//    addition       → multiplication ( ( "-" | "+" ) multiplication )* ;
//    multiplication → unary ( ( "/" | "*" ) unary )* ;
//    unary          → ( "!" | "-" ) unary ;
//                   | primary ;
//    primary        → NUMBER | STRING | "false" | "true" | "nil"
//                   | "(" expression ")" ;
public class Parser {
    private static class ParseError extends RuntimeException {
    }

    private final List<Token> tokens;
    private int current = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(statement());
        }

        return statements;
    }

    private boolean check(TokenType tokenType) {
        return !isAtEnd() && peek().type == tokenType;
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private boolean isAtEnd() {
        return peek().type == EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    // grammar
    private Stmt statement() {
        if (match(PRINT)) return printStatement();

        return expressionStatement();
    }

    private Stmt printStatement() {
        Expr value = expression();
        consume(SEMICOLON, "Expect ';' after value.");
        return new Stmt.Print(value);
    }

    private Stmt expressionStatement() {
        Expr expr = expression();
        consume(SEMICOLON, "Expect ';' after expression.");
        return new Stmt.Expression(expr);
    }

    private Expr expression() {
        return equality();
    }

    private Expr equality() {
        Expr expr = comparison();
        while (match(BANG_EQUAL, EQUAL_EQUAL)) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr comparison() {
        Expr expr = addition();
        while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
            Token operator = previous();
            Expr right = addition();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr addition() {
        Expr expr = multiplication();
        while (match(MINUS, PLUS)) {
            Token operator = previous();
            Expr right = multiplication();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;

    }

    private Expr multiplication() {
        Expr expr = unary();

        while (match(SLASH, STAR)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;
    }

    private Expr unary() {
        if (match(BANG, MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Expr.Unary(operator, right);
        }

        return primary();
    }

    private Expr primary() {
        if (match(FALSE)) return new Expr.Literal(false);
        if (match(TRUE)) return new Expr.Literal(true);
        if (match(NIL)) return new Expr.Literal(null);

        if (match(NUMBER, STRING)) {
            return new Expr.Literal(previous().literal);
        }

        if (match(LEFT_PAREN)) {
            Expr expr = expression();
            consume(RIGHT_PAREN, "Expect ')' after expression.");
            return new Expr.Grouping(expr);
        }

        throw error(peek(), "Expect expression.");
    }

    private Token consume(TokenType type, String message) {
        if (check(type)) return advance();

        throw error(peek(), message);
    }

    private void synchronize() {
        advance();

        while (!isAtEnd()) {
            if (previous().type == SEMICOLON) return;

            switch (peek().type) {
                case CLASS:
                case FUN:
                case VAR:
                case FOR:
                case IF:
                case WHILE:
                case PRINT:
                case RETURN:
                    return;
                default:
                    break;
            }

            advance();
        }
    }

    private ParseError error(Token token, String message) {
        JLox.error(token, message);
        return new ParseError();
    }
}
