#!/usr/bin/env python3
# encoding: utf-8

def f(ham: 42, eggs: int = 'spam') -> "Nothing to see here":
    print("Annotations:", f.__annotations__)
    print("Argument:", ham, eggs)

f('wonderful')

