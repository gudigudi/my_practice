#!/usr/bin/env python3
# encoding: utf-8

"""
Crab
Recommendation System module in python
######################################

scikits.recommander is a Python mon integrating classique recommandation
algorithms in the tightly-nit world of scientific Python
package (numpy, scipy, matplotlib).

It aims to provide simple and efficient solutions to recommandation problems
that are accessible to everybody and reusable in various contexts:
being a flexible tool for scientific and industry purposes.

See http://github.com/muricoca/crab for complete documentation.

"""

import logging

try:
    from numpy.testing import nosetester

    class NoseTester(nosetester.NoseTester):
        """
        Subclass numpy's NoseTester to add doctests by default
        """
        def test(self, label='fast', verbose=1, extra_argv=['--exe'],
                 doctests=True, coverage=False):
            return super(NoseTester, self).test(label=label, verbose=verbose,
                    extra_argv=extra_argv, doctests=doctests, coverage=coverage)
    test = NoseTester().test
    del nosetester
except :
    pass

__all__ = ['datasets', 'metrics', 'similarities', 'models', 'recommanders']

__version__ = '0.1.git'

class NullHandler(logging.Handler):
    """For python versions <= 2.6; same as `logging.NullHandler` in 2.7."""
    def emit(self, record):
        pass

logger = logging.getLogger('crab')
if len(logger.handlers) == 0:  # To ensure reload() doesn't add another one
    logger.addHandler(NullHandler())

