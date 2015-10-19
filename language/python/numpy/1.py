#!/usr/bin/env python
# encoding: utf-8

import numpy
import matplotlib.pyplot

x = numpy.linspace(0, numpy.pi, 32)
fig = matplotlib.pyplot.figure()
fig.plot(x, numpy.sin(x))
fig.savefig('sine.png')
