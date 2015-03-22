#!/usr/bin/env python
# encoding: utf-8

from pylab import *

figure(figsize=(8, 6), dpi=80)

subplot(1, 1, 1)

X = np.linspace(-np.pi, np.pi, 256, endpoint=True)
C, S = np.cos(X), np.sin(X)

plot(X, S, color="blue", linewidth=1.0, linestyle="-")
plot(X, C, color="green", linewidth=1.0, linestyle="-")

xlim(-4.0, 4.0)

xticks(np.linspace(-4, 4, 9, endpoint=True))

ylim(-1.0, 1.0)

yticks(np.linspace(-1, 1, 4, endpoint=True))

show()
