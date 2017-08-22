import argparse

import os
import re

import sys

here = os.path.abspath(os.path.dirname(__file__))

DISABLED_TESTS = [
    ('benchmark', 'binary_trees.lox'),
    ('benchmark', 'equality.lox'),
    ('benchmark', 'fib.lox'),
    ('benchmark', 'invocation.lox'),
    ('benchmark', 'method_call.lox'),
    ('benchmark', 'properties.lox'),
    ('benchmark', 'string_equality.lox'),
    ('expressions', 'evaluate.lox'),
    ('expressions', 'parse.lox'),
    ('limit', 'loop_too_large.lox'),
    ('limit', 'stack_overflow.lox'),
    ('limit', 'too_many_constants.lox'),
    ('limit', 'too_many_locals.lox'),
    ('limit', 'too_many_upvalues.lox'),
    ('scanning', 'identifiers.lox'),
    ('scanning', 'keywords.lox'),
    ('scanning', 'numbers.lox'),
    ('scanning', 'punctuators.lox'),
    ('scanning', 'strings.lox'),
    ('scanning', 'whitespace.lox'),
]

OUTPUT_EXPECT = r'// expect: ?(.*)'
ERROR_EXPECT = r'// (Error.*)'
ERROR_LINE_EXPECT = r'// \[((java|c) )?line (\d+)\] (Error.*)'
RUNTIME_ERROR_EXPECT = r'// expect runtime error: (.+)'
SYNTAX_ERROR_RE = r'\[.*line (\d+)\] (Error.+)'
STACK_TRACE_RE = r'\[line (\d+)\]'
NONTEST_RE = r'// nontest'


def os_path_split_recursive(path):
    rest, tail = os.path.split(path)
    if rest == '':
        return tail,
    return os_path_split_recursive(rest) + (tail,)


def main(path):
    gen_path = os.path.realpath(path)
    test_programs_path = os.path.join(here, '../../jlox/src/test/resources/programs')
    test_programs_relative_path = os.path.relpath(os.path.realpath(test_programs_path), gen_path)
    test_programs_relative_path_parts = os_path_split_recursive(test_programs_relative_path)
    test_programs_relative_path_len = len(test_programs_relative_path_parts)
    lox_groups = {}
    for root, dirs, files in os.walk(test_programs_path):
        for file in files:
            a = file.endswith('empty_file.lox')
            if file.endswith('.lox'):
                real_path = os.path.realpath(os.path.join(root, file))
                relative_path = os.path.relpath(real_path, gen_path)
                relative_path_parts = os_path_split_recursive(relative_path)
                relative_path_len = len(relative_path_parts)
                if relative_path_len == test_programs_relative_path_len + 1:  # files with no group
                    group = None
                else:
                    group = relative_path_parts[-2]
                if group not in lox_groups:
                    lox_groups[group] = {
                        'files': []
                    }
                lox_groups[group]['files'].append({
                    'real_path': real_path,
                    'path': relative_path,
                    'path_parts': relative_path_parts,
                    'name': os.path.splitext(relative_path_parts[-1])[0]
                })
    for group_name, group in lox_groups.items():
        group_test_path = os.path.join(gen_path,
                                       'test_interpreter{1}{0}.py'.format(group_name if group_name is not None else '',
                                                                          '_' if group_name is not None else ''))
        # print(group_name, group_test_path, group['files'])
        code = [
            'import os',
            '',
            'from tests import InterpreterTestHelper',
            ''
        ]
        for file in group['files']:
            disabled = file['path_parts'][-2:] in DISABLED_TESTS
            if disabled:
                print('! Disabling', file['name'], 'in', group_test_path)
            code.append('')
            code.append('def {1}test_{0}():'.format(file['name'], 'disabled_' if disabled else ''))
            code.append('    helper = InterpreterTestHelper(')
            code.append('        source_file=os.path.join(')
            code.append('            {}'.format(', '.join(map(lambda p: '\'{}\''.format(p), file['path_parts'][1:]))))
            code.append('        )')
            code.append('    )')
            code.append('    helper.run()')
            code.append('    out = helper.get_output()')
            code.append('    err = helper.get_error()')
            output_expect = []
            error_expect = []
            with open(file=file['real_path'], mode='r', encoding='utf8') as program_file:
                program_lines = program_file.read().splitlines(keepends=False)
                for line_number, program_line in enumerate(program_lines):
                    output_expect_match = re.search(OUTPUT_EXPECT, program_line)
                    if output_expect_match:
                        output_expect.append(output_expect_match.group(1))

                    error_expect_match = re.search(ERROR_EXPECT, program_line)
                    if error_expect_match:
                        error_expect.append(error_expect_match.group(1))

                    error_line_expect_match = re.search(ERROR_LINE_EXPECT, program_line)
                    if error_line_expect_match:
                        language = error_line_expect_match.group(2)
                        if language is None or 'java' == language:
                            error_expect.append(error_line_expect_match.group(4))

                    runtime_error_expect_match = re.search(RUNTIME_ERROR_EXPECT, program_line)
                    if runtime_error_expect_match:
                        error_expect.append(runtime_error_expect_match.group(1))
                        error_expect.append('[line {}]'.format(line_number + 1))
            if not output_expect:
                code.append('    assert [\'\'] == out')
            else:
                code.append('    assert {} == len(out)'.format(len(output_expect)))
                for i, ex in enumerate(output_expect):
                    code.append('    assert \'{1}\' in out[{0}]'.format(i, ex.replace('\'', '\\\'')))
            if not error_expect:
                code.append('    assert [\'\'] == err')
            else:
                code.append('    assert {} == len(err)'.format(len(error_expect)))
                for i, ex in enumerate(error_expect):
                    code.append('    assert \'{1}\' in err[{0}]'.format(i, ex.replace('\'', '\\\'')))
            code.append('')

        with open(group_test_path, 'w', encoding='utf8') as generated_file:
            generated_file.writelines('\n'.join(code))
        print('Generated', group_test_path)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Regenerate all the test files')
    parser.add_argument('DIR', help='The DIR where to put the generated test files')
    args = parser.parse_args()
    if not os.path.isdir(args.DIR):
        print('[fail] DIR argument should be an existing directory.', file=sys.stderr)
        parser.print_help(file=sys.stderr)
        sys.exit(1)
    main(args.DIR)
