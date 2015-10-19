#!/usr/bin/env python3
# encoding: utf-8

from string import Template

format = "Hello, %s, %s, enough for ya?"
values = ('world', 'Hot')
print(format % values)

s = Template('$x, gloriout $x!')
s.substitute(x='slurm')
print(s)
