from __future__ import print_function
from setuptools import setup
from setuptools.command.test import test as TestCommand
import io
import os
import sys

import pylox

here = os.path.abspath(os.path.dirname(__file__))


def read(*filenames, **kwargs):
    encoding = kwargs.get('encoding', 'utf-8')
    sep = kwargs.get('sep', '\n')
    buf = []
    for filename in filenames:
        with io.open(filename, encoding=encoding) as f:
            buf.append(f.read())
    return sep.join(buf)


long_description = read('README.txt')


class PyTest(TestCommand):
    def finalize_options(self):
        TestCommand.finalize_options(self)
        self.test_args = []
        self.test_suite = True

    def run_tests(self):
        import pytest
        errcode = pytest.main(self.test_args)
        sys.exit(errcode)


setup(
    name='pylox',
    version=pylox.__version__,
    url='http://github.com/ocus/lox/',
    license='Apache Software License',
    author='Matthieu Honel',
    tests_require=['pytest', 'pytest-cov'],
    install_requires=[],
    cmdclass={'test': PyTest},
    author_email='ocus51@gmail.com.com',
    description='A Lox interpreter',
    long_description=long_description,
    packages=['pylox', 'pylox.scripts'],
    entry_points={
        'console_scripts': [
            'pylox = pylox.scripts.pylox:run_pylox',
        ],
    },
    include_package_data=True,
    platforms='any',
    test_suite='tests.test_pylox',
    classifiers=[
        'Programming Language :: Python',
        'Development Status :: 4 - Beta',
        'Natural Language :: English',
        'Environment :: Web Environment',
        'Intended Audience :: Developers',
        'License :: OSI Approved :: Apache Software License',
        'Operating System :: OS Independent',
        'Topic :: Software Development :: Libraries :: Python Modules',
        'Topic :: Software Development :: Libraries :: Application Frameworks',
    ]
)
