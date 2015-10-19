#!/usr/bin/env python3
# encoding: utf-8

def make_incrementor(n):
    return lambda x: x + n

f = make_incrementor(42)

paris = [(1, 'one'), (2, 'two'), (3, 'three'), (4, 'four')]
paris.sort(key=lambda pair: pair[1])
print(paris)
