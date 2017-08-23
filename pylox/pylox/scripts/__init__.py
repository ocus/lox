import argparse

from pylox import PyLox


def run_pylox():
    parser = argparse.ArgumentParser(description='Run the PyLox interpreter')
    parser.add_argument('FILE', help='The LOX file to be executed', nargs='?')
    parser.add_argument('-a', '--action', help='The type of action to perform', choices=('run', 'dump'), default='run')
    args = parser.parse_args()
    if args.FILE:
        getattr(PyLox, '{}_file'.format(args.action))(file_path=args.FILE)
    else:
        getattr(PyLox, '{}_prompt'.format(args.action))()
