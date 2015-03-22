#!/usr/bin/env python
# encoding: utf-8

def fob(n):
    a, b = 0, 1
    while a < n:
        print(a)
        a, b = b, a+b
fob(42)
