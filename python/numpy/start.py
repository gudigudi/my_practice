#!/usr/bin/env python
# encoding: utf-8

import numpy

def numpysum(n):
    a = numpy.arange(n) ** 2
    b = numpy.arange(n) ** 3
    return a + b

print(numpysum(100000))
