from abc import ABC, abstractmethod
from typing import List

from .expr import AbstractExpr
from .stmt import AbstractStmt
from .token import Token


class ScannerInterface(ABC):
    @abstractmethod
    def scan_tokens(self) -> List[Token]:
        pass


class ParserInterface(ABC):
    @abstractmethod
    def parse(self) -> List[AbstractStmt]:
        pass


class EnvironmentInterface(ABC):
    @abstractmethod
    def define(self, name: str, value):
        pass

    @abstractmethod
    def assign(self, token: Token, value):
        pass

    @abstractmethod
    def get(self, token: Token):
        pass

    @abstractmethod
    def assign_at(self, distance: int, token: Token, value):
        pass

    @abstractmethod
    def get_at(self, distance: int, name: str):
        pass


class InterpreterInterface(ABC):
    @abstractmethod
    def interpret(self, statements: List[AbstractStmt]):
        pass

    @abstractmethod
    def execute_block(self, statements: List[AbstractStmt], environment: EnvironmentInterface):
        pass

    @abstractmethod
    def resolve(self, expr: AbstractExpr, depth: int):
        pass

    @abstractmethod
    def get_env(self):
        pass
