#!/usr/bin/env python3
# encoding: utf-8

def f(x, y=[]):
    y.append(x)
    return y

print(f(23))
print(f(24))

def sum_args(*args):
    return sum(args)

print(sum_args(1, 2, 3, 4, 5))


