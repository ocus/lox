import argparse

import os

import sys


# noinspection PyListCreation
def define_ast(file_path, base_name, types, extra_head=None):
    code = []

    code.append('from abc import ABC, abstractmethod')

    if extra_head:
        code.append(extra_head)
        code.append('')

    # code.append('')
    # code.append('class {}:'.format(base_name))
    # for t in types:
    #     class_name = t.split(':')[0].strip()
    #     code.append('    {} = None'.format(class_name))
    # code.append('')
    code.append('')
    code.append('class Abstract{}(ABC):'.format(base_name))
    code.append('    @abstractmethod')
    code.append('    def accept(self, visitor: \'Abstract{}.Visitor\'):'.format(base_name))
    code.append('        pass')
    code.append('')
    code.append('    class Visitor(ABC):')

    for t in types:
        class_name = t.split(':')[0].strip()
        code.append('        @abstractmethod')
        code.append(
            '        def visit_{1}_{0}(self, {0}: \'{2}{3}\'):'.format(base_name.lower(), class_name.lower(),
                                                                         base_name,
                                                                         class_name))
        code.append('            pass')
        code.append('')
    code.append('')

    def get_init_fields(field_list):
        fields = []
        for field in field_list.split(', '):
            name = field.split(' ')[1]
            type = field.split(' ')[0]
            fields.append('{0}: \'{1}\''.format(name, type))
        return ', '.join(fields)

    def define_type(base_name, class_name, field_list):
        code.append('class {0}{1}(Abstract{0}):'.format(base_name, class_name))
        code.append('    def __init__(self, {}):'.format(get_init_fields(field_list)))
        for field in field_list.split(', '):
            code.append('        self.{0} = {0}'.format(field.split(' ')[1], field.split(' ')[0]))
        code.append('')
        code.append('    def accept(self, visitor):')
        code.append('        return visitor.visit_{1}_{0}({0}=self)'.format(base_name.lower(), class_name.lower()))
        code.append('')
        # code.append('')
        # code.append('{0}.{1} = _{0}{1}'.format(base_name, class_name))
        # code.append('')

    for t in types:
        class_name = t.split(':')[0].strip()
        fields = t.split(':')[1].strip()
        define_type(base_name, class_name, fields)
        code.append('')

    with open(file=file_path, mode='w') as f:
        f.writelines('\n'.join(code))

    print('{} written.'.format(file_path))


def main(directory):
    define_ast(
        os.path.join(directory, 'expr.py'),
        'Expr', [
            'Assign   : Token name, AbstractExpr value',
            'Binary   : AbstractExpr left, Token operator, AbstractExpr right',
            'Call     : AbstractExpr callee, Token paren, List[AbstractExpr] arguments',
            'Get      : AbstractExpr object, Token name',
            'Grouping : AbstractExpr expression',
            'Literal  : PyLoxLiteral value',
            'Logical  : AbstractExpr left, Token operator, AbstractExpr right',
            'Set      : AbstractExpr object, Token name, AbstractExpr value',
            'This     : Token keyword',
            'Super    : Token keyword, Token method',
            'Unary    : Token operator, AbstractExpr right',
            'Variable : Token name'
        ],
        extra_head="""from typing import Union, List

from .token import Token

PyLoxLiteral = Union[float, str, None]"""
    )
    define_ast(
        os.path.join(directory, 'stmt.py'),
        'Stmt', [
            'Block      : List[AbstractStmt] statements',
            'Class      : Token name, AbstractExpr superclass, List[StmtFunction] methods',
            'Expression : AbstractExpr expression',
            'Function   : Token name, List[Token] parameters, List[AbstractStmt] body',
            'If         : AbstractExpr condition, AbstractStmt then_branch, AbstractStmt else_branch',
            'Print      : AbstractExpr expression',
            'Var        : Token name, AbstractExpr initializer',
            'Return     : Token keyword, AbstractExpr value',
            'While      : AbstractExpr condition, AbstractStmt body'
        ],
        extra_head="""from typing import List

from .expr import AbstractExpr
from .token import Token"""
    )


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Regenerate the expr.py and stmt.py files')
    parser.add_argument('DIR', help='The DIR where to put the generated AST files')
    args = parser.parse_args()
    if not os.path.isdir(args.DIR):
        print('[fail] DIR argument should be an existing directory.', file=sys.stderr)
        parser.print_help(file=sys.stderr)
        sys.exit(1)
    main(args.DIR)
