from enum import Enum


def _inc_gen(n):
    while True:
        yield n
        n += 1


_inc = _inc_gen(1)


class TokenType(Enum):
    # Single-character tokens.
    LEFT_PAREN = next(_inc)
    RIGHT_PAREN = next(_inc)
    LEFT_BRACE = next(_inc)
    RIGHT_BRACE = next(_inc)
    COMMA = next(_inc)
    DOT = next(_inc)
    MINUS = next(_inc)
    PLUS = next(_inc)
    SEMICOLON = next(_inc)
    SLASH = next(_inc)
    STAR = next(_inc)

    # One or two character tokens.
    BANG = next(_inc)
    BANG_EQUAL = next(_inc)
    EQUAL = next(_inc)
    EQUAL_EQUAL = next(_inc)
    GREATER = next(_inc)
    GREATER_EQUAL = next(_inc)
    LESS = next(_inc)
    LESS_EQUAL = next(_inc)

    # Literals.
    IDENTIFIER = next(_inc)
    STRING = next(_inc)
    NUMBER = next(_inc)

    # Keywords.
    AND = next(_inc)
    CLASS = next(_inc)
    ELSE = next(_inc)
    FALSE = next(_inc)
    FUN = next(_inc)
    FOR = next(_inc)
    IF = next(_inc)
    NIL = next(_inc)
    OR = next(_inc)
    PRINT = next(_inc)
    RETURN = next(_inc)
    SUPER = next(_inc)
    THIS = next(_inc)
    TRUE = next(_inc)
    VAR = next(_inc)
    WHILE = next(_inc)

    EOF = next(_inc)


class Token(object):
    def __init__(self, token_type, lexeme, literal, line):
        self.type = token_type
        self.lexeme = lexeme
        self.literal = literal
        self.line = line

    def __str__(self):
        return 'Token({},{},{})'.format(self.type, self.lexeme, self.literal)
