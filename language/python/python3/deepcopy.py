#!/usr/bin/env python3
# encoding: utf-8

from copy import deepcopy

d = {}
d['names'] = ['Alfred', 'Bertrand']
c = d.copy()
dc = deepcopy(d)

d['names'].append('Clive')

print(c)
print(dc)
