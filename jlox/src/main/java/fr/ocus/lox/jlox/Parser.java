package fr.ocus.lox.jlox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.ocus.lox.jlox.TokenType.AND;
import static fr.ocus.lox.jlox.TokenType.BANG;
import static fr.ocus.lox.jlox.TokenType.BANG_EQUAL;
import static fr.ocus.lox.jlox.TokenType.ELSE;
import static fr.ocus.lox.jlox.TokenType.EOF;
import static fr.ocus.lox.jlox.TokenType.EQUAL;
import static fr.ocus.lox.jlox.TokenType.EQUAL_EQUAL;
import static fr.ocus.lox.jlox.TokenType.FALSE;
import static fr.ocus.lox.jlox.TokenType.FOR;
import static fr.ocus.lox.jlox.TokenType.GREATER;
import static fr.ocus.lox.jlox.TokenType.GREATER_EQUAL;
import static fr.ocus.lox.jlox.TokenType.IDENTIFIER;
import static fr.ocus.lox.jlox.TokenType.IF;
import static fr.ocus.lox.jlox.TokenType.LEFT_BRACE;
import static fr.ocus.lox.jlox.TokenType.LEFT_PAREN;
import static fr.ocus.lox.jlox.TokenType.LESS;
import static fr.ocus.lox.jlox.TokenType.LESS_EQUAL;
import static fr.ocus.lox.jlox.TokenType.MINUS;
import static fr.ocus.lox.jlox.TokenType.NIL;
import static fr.ocus.lox.jlox.TokenType.NUMBER;
import static fr.ocus.lox.jlox.TokenType.OR;
import static fr.ocus.lox.jlox.TokenType.PLUS;
import static fr.ocus.lox.jlox.TokenType.PRINT;
import static fr.ocus.lox.jlox.TokenType.RIGHT_BRACE;
import static fr.ocus.lox.jlox.TokenType.RIGHT_PAREN;
import static fr.ocus.lox.jlox.TokenType.SEMICOLON;
import static fr.ocus.lox.jlox.TokenType.SLASH;
import static fr.ocus.lox.jlox.TokenType.STAR;
import static fr.ocus.lox.jlox.TokenType.STRING;
import static fr.ocus.lox.jlox.TokenType.TRUE;
import static fr.ocus.lox.jlox.TokenType.VAR;
import static fr.ocus.lox.jlox.TokenType.WHILE;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 *
 * Grammar: (https://github.com/munificent/craftinginterpreters/blob/master/note/grammar.txt)
 * <pre>
 * program        → declaration* EOF ;
 *
 * declaration    → varDecl
 *                | statement ;
 *
 * statement      → exprStmt
 *                | forStmt
 *                | ifStmt
 *                | printStmt
 *                | whileStmt
 *                | block ;
 *
 * varDecl        → "var" IDENTIFIER ( "=" expression )? ";" ;
 *
 * exprStmt       → expression ";" ;
 * forStmt        → "for" "(" ( varDecl | exprStmt | ";" )
 *                            expression? ";"
 *                            expression? ")" statement ;
 * ifStmt         → "if" "(" expression ")" statement ( "else" statement )? ;
 * printStmt      → "print" expression ";" ;
 * whileStmt      → "while" "(" expression ")" statement ;
 *
 * block          → "{" declaration* "}" ;
 *
 * expression     → assignment ;
 * assignment     → identifier "=" assignment
 *                | logic_or ;
 * logic_or       → logic_and ( "or" logic_and )* ;
 * logic_and      → equality ( "and" equality )* ;
 * equality       → comparison ( ( "!=" | "==" ) comparison )* ;
 * comparison     → addition ( ( ">" | ">=" | "<" | "<=" ) addition )* ;
 * addition       → multiplication ( ( "-" | "+" ) multiplication )* ;
 * multiplication → unary ( ( "/" | "*" ) unary )* ;
 * unary          → ( "!" | "-" ) unary ;
 *                | primary ;
 * primary        → "false" | "true" | "nil"
 *                | NUMBER | STRING
 *                | "(" expression ")"
 *                | IDENTIFIER ;
 *
 * NUMBER         → DIGIT+ ( "." DIGIT* )? | "." DIGIT+ ;
 * STRING         → '"' <any char except '"'>* '"' ;
 * IDENTIFIER     → ALPHA ( ALPHA | DIGIT )* ;
 * ALPHA          → 'a' ... 'z' | 'A' ... 'Z' | '_' ;
 * DIGIT          → '0' ... '9' ;
 * </pre>
 */
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
            statements.add(declaration());
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
    private Stmt declaration() {
        try {
            if (match(VAR)) return varDeclaration();

            return statement();
        } catch (ParseError error) {
            synchronize();
            return null;
        }
    }

    private Stmt varDeclaration() {
        Token name = consume(IDENTIFIER, "Expect variable name.");

        Expr initializer = null;
        if (match(EQUAL)) {
            initializer = expression();
        }

        consume(SEMICOLON, "Expect ';' after variable declaration.");
        return new Stmt.Var(name, initializer);
    }

    private Stmt statement() {
        if (match(FOR)) return forStatement();
        if (match(IF)) return ifStatement();
        if (match(PRINT)) return printStatement();
        if (match(WHILE)) return whileStatement();
        if (match(LEFT_BRACE)) return new Stmt.Block(block());

        return expressionStatement();
    }

    private Stmt forStatement() {
        consume(LEFT_PAREN, "Expect '(' after 'for'.");

        Stmt initializer;
        if (match(SEMICOLON)) {
            initializer = null;
        } else if (match(VAR)) {
            initializer = varDeclaration();
        } else {
            initializer = expressionStatement();
        }

        Expr condition = null;
        if (!check(SEMICOLON)) {
            condition = expression();
        }
        consume(SEMICOLON, "Expect ';' after loop condition.");

        Expr increment = null;
        if (!check(RIGHT_PAREN)) {
            increment = expression();
        }
        consume(RIGHT_PAREN, "Expect ')' after for clauses.");

        Stmt body = statement();
        if (increment != null) {
            body = new Stmt.Block(Arrays.asList(body, new Stmt.Expression(increment)));
        }

        if (condition == null) condition = new Expr.Literal(true);
        body = new Stmt.While(condition, body);

        if (initializer != null) {
            body = new Stmt.Block(Arrays.asList(initializer, body));
        }

        return body;
    }

    private Stmt ifStatement() {
        consume(LEFT_PAREN, "Expect '(' after 'if'.");
        Expr condition = expression();
        consume(RIGHT_PAREN, "Expect ')' after if condition.");

        Stmt thenBranch = statement();
        Stmt elseBranch = null;
        if (match(ELSE)) {
            elseBranch = statement();
        }

        return new Stmt.If(condition, thenBranch, elseBranch);
    }

    private Stmt printStatement() {
        Expr value = expression();
        consume(SEMICOLON, "Expect ';' after value.");
        return new Stmt.Print(value);
    }

    private Stmt whileStatement() {
        consume(LEFT_PAREN, "Expect '(' after 'while'.");
        Expr condition = expression();
        consume(RIGHT_PAREN, "Expect ')' after condition.");
        Stmt body = statement();

        return new Stmt.While(condition, body);
    }

    private Stmt expressionStatement() {
        Expr expr = expression();
        consume(SEMICOLON, "Expect ';' after expression.");
        return new Stmt.Expression(expr);
    }

    private List<Stmt> block() {
        List<Stmt> statements = new ArrayList<>();

        while (!check(RIGHT_BRACE) && !isAtEnd()) {
            statements.add(declaration());
        }

        consume(RIGHT_BRACE, "Expect '}' after block.");
        return statements;
    }

    private Expr expression() {
        return assignment();
    }

    private Expr assignment() {
        Expr expr = or();

        if (match(EQUAL)) {
            Token equals = previous();
            Expr value = assignment();

            if (expr instanceof Expr.Variable) {
                Token name = ((Expr.Variable) expr).name;
                return new Expr.Assign(name, value);
            }

            error(equals, "Invalid assignment target.");
        }

        return expr;
    }

    private Expr or() {
        Expr expr = and();

        while (match(OR)) {
            Token operator = previous();
            Expr right = and();
            expr = new Expr.Logical(expr, operator, right);
        }

        return expr;
    }

    private Expr and() {
        Expr expr = equality();

        while (match(AND)) {
            Token operator = previous();
            Expr right = equality();
            expr = new Expr.Logical(expr, operator, right);
        }

        return expr;
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

        if (match(IDENTIFIER)) {
            return new Expr.Variable(previous());
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
