package fr.ocus.lox.jlox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.ocus.lox.jlox.TokenType.AND;
import static fr.ocus.lox.jlox.TokenType.BANG;
import static fr.ocus.lox.jlox.TokenType.BANG_EQUAL;
import static fr.ocus.lox.jlox.TokenType.CLASS;
import static fr.ocus.lox.jlox.TokenType.COMMA;
import static fr.ocus.lox.jlox.TokenType.DOT;
import static fr.ocus.lox.jlox.TokenType.ELSE;
import static fr.ocus.lox.jlox.TokenType.EOF;
import static fr.ocus.lox.jlox.TokenType.EQUAL;
import static fr.ocus.lox.jlox.TokenType.EQUAL_EQUAL;
import static fr.ocus.lox.jlox.TokenType.FALSE;
import static fr.ocus.lox.jlox.TokenType.FOR;
import static fr.ocus.lox.jlox.TokenType.FUN;
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
import static fr.ocus.lox.jlox.TokenType.RETURN;
import static fr.ocus.lox.jlox.TokenType.RIGHT_BRACE;
import static fr.ocus.lox.jlox.TokenType.RIGHT_PAREN;
import static fr.ocus.lox.jlox.TokenType.SEMICOLON;
import static fr.ocus.lox.jlox.TokenType.SLASH;
import static fr.ocus.lox.jlox.TokenType.STAR;
import static fr.ocus.lox.jlox.TokenType.STRING;
import static fr.ocus.lox.jlox.TokenType.SUPER;
import static fr.ocus.lox.jlox.TokenType.THIS;
import static fr.ocus.lox.jlox.TokenType.TRUE;
import static fr.ocus.lox.jlox.TokenType.VAR;
import static fr.ocus.lox.jlox.TokenType.WHILE;

/**
 * @author Matthieu Honel <ocus51@gmail.com>
 * @since 2017-08-03
 */
class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            /* @formatter:off */
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;
            case '!': addToken(match('=') ? BANG_EQUAL : BANG); break;
            case '=': addToken(match('=') ? EQUAL_EQUAL : EQUAL); break;
            case '<': addToken(match('=') ? LESS_EQUAL : LESS); break;
            case '>': addToken(match('=') ? GREATER_EQUAL : GREATER); break;
            case '"': string(); break;
            /* @formatter:on */
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(SLASH);
                }
                break;
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                break;
            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    JLox.error(line, "Unexpected character.");
                }
                break;
        }
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;
        current++;
        return true;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            JLox.error(line, "Unterminated string.");
            return;
        }
        advance(); // closing "

        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    private void number() {
        while (isDigit(peek())) advance();

        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }

        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();

        // see if the identifier is reserved word.
        String text = source.substring(start, current);

        TokenType type = keywords.get(text);
        if (type == null) type = IDENTIFIER;
        addToken(type);
    }

    private void addToken(TokenType type) {
        addToken(type, null);

    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        /* @formatter:off */
        keywords.put("and",    AND);
        keywords.put("class",  CLASS);
        keywords.put("else",   ELSE);
        keywords.put("false",  FALSE);
        keywords.put("for",    FOR);
        keywords.put("fun",    FUN);
        keywords.put("if",     IF);
        keywords.put("nil",    NIL);
        keywords.put("or",     OR);
        keywords.put("print",  PRINT);
        keywords.put("return", RETURN);
        keywords.put("super",  SUPER);
        keywords.put("this",   THIS);
        keywords.put("true",   TRUE);
        keywords.put("var",    VAR);
        keywords.put("while",  WHILE);
        /* @formatter:on */
    }
}
