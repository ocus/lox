from typing import List

from .interface import ScannerInterface
from .token import Token, TokenType


class Scanner(ScannerInterface):
    def __init__(self, source: str):
        self._source = source
        self._tokens = []  # type: List[Token]
        self._start = 0
        self._current = 0
        self._line = 1

    def scan_tokens(self) -> List[Token]:
        while not self._is_at_end():
            self._start = self._current
            self._scan_token()

        self._tokens.append(Token(TokenType.EOF, '', None, self._line))
        return self._tokens

    def _is_at_end(self):
        return self._current >= len(self._source)

    def _scan_token(self):
        c = self._advance()
        if c == '(':
            self._add_token(TokenType.LEFT_PAREN)
        elif c == ')':
            self._add_token(TokenType.RIGHT_PAREN)
        elif c == '{':
            self._add_token(TokenType.LEFT_BRACE)
        elif c == '}':
            self._add_token(TokenType.RIGHT_BRACE)
        elif c == ',':
            self._add_token(TokenType.COMMA)
        elif c == '.':
            self._add_token(TokenType.DOT)
        elif c == '-':
            self._add_token(TokenType.MINUS)
        elif c == '+':
            self._add_token(TokenType.PLUS)
        elif c == ';':
            self._add_token(TokenType.SEMICOLON)
        elif c == '*':
            self._add_token(TokenType.STAR)
        elif c == '!':
            self._add_token(TokenType.BANG_EQUAL if self._match('=') else TokenType.BANG)
        elif c == '=':
            self._add_token(TokenType.EQUAL_EQUAL if self._match('=') else TokenType.EQUAL)
        elif c == '<':
            self._add_token(TokenType.LESS_EQUAL if self._match('=') else TokenType.LESS)
        elif c == '>':
            self._add_token(TokenType.GREATER_EQUAL if self._match('=') else TokenType.GREATER)
        elif c == '/':
            if self._match('/'):
                while self._peek() != '\n' and not self._is_at_end():
                    self._advance()
            else:
                self._add_token(TokenType.SLASH)
        elif c in (' ', '\r', '\t'):
            pass  # ignore whitespace.
        elif c == "\n":
            self._line += 1  # incoming next line
        elif c == '"':
            self._string()
        else:
            if self._is_digit(c):
                self._number()
            elif self._is_alpha(c):
                self._identifier()
            else:
                from pylox import PyLox
                PyLox.error(self._line, 'Unexpected character.')

    def _add_token(self, token_type, literal=None):
        text = self._source[self._start:self._current]
        self._tokens.append(Token(token_type, text, literal, self._line))

    def _advance(self):
        self._current += 1
        return self._source[self._current - 1]

    def _match(self, expected):
        if self._is_at_end():
            return False
        if self._source[self._current] != expected:
            return False

        self._current += 1
        return True

    def _peek(self):
        if self._is_at_end():
            return '\0'
        return self._source[self._current]

    def _peek_next(self):
        if self._current + 1 >= len(self._source):
            return '\0'
        return self._source[self._current + 1]

    def _string(self):
        while self._peek() != '"' and not self._is_at_end():
            if self._peek() == '\n':
                self._line += 1
            self._advance()

        # Unterminated string
        if self._is_at_end():
            from pylox import PyLox
            PyLox.error(self._line, "Unterminated string.")

        # the closing "
        self._advance()

        value = self._source[self._start + 1: self._current - 1]
        self._add_token(TokenType.STRING, value)

    def _number(self):
        while self._is_digit(self._peek()):
            self._advance()

        if self._peek() == '.' and self._is_digit(self._peek_next()):
            self._advance()  # consume the "."
            while self._is_digit(self._peek()):
                self._advance()

        value = float(self._source[self._start:self._current])
        self._add_token(TokenType.NUMBER, value)

    def _identifier(self):
        while self._is_alpha_numeric(self._peek()):
            self._advance()

        # reserved word ?
        text = self._source[self._start:self._current]
        token_type = Scanner.KEYWORDS[text] if text in Scanner.KEYWORDS else TokenType.IDENTIFIER

        self._add_token(token_type)

    @staticmethod
    def _is_digit(c):
        return c.isdigit()

    @staticmethod
    def _is_alpha(c):
        return c.isalpha() or '_' == c

    def _is_alpha_numeric(self, c):
        return self._is_alpha(c) or self._is_digit(c)

    KEYWORDS = {
        'and': TokenType.AND,
        'class': TokenType.CLASS,
        'else': TokenType.ELSE,
        'false': TokenType.FALSE,
        'for': TokenType.FOR,
        'fun': TokenType.FUN,
        'if': TokenType.IF,
        'nil': TokenType.NIL,
        'or': TokenType.OR,
        'print': TokenType.PRINT,
        'return': TokenType.RETURN,
        'super': TokenType.SUPER,
        'this': TokenType.THIS,
        'true': TokenType.TRUE,
        'var': TokenType.VAR,
        'while': TokenType.WHILE,
    }
