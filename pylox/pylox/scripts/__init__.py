import argparse

from pylox import PyLox


def run_pylox():
    parser = argparse.ArgumentParser(description='Run the PyLox interpreter')
    parser.add_argument('FILE', help='The LOX file to be executed', nargs='?')
    args = parser.parse_args()
    if args.FILE:
        PyLox.run_file(file_path=args.FILE)
    else:
        PyLox.run_prompt()
