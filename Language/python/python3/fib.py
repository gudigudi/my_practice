#!/usr/bin/env python
# encoding: utf-8

def fib(n):    # write Fibonacci series up to n
    """Print a Fibonacci series up to n."""
    a, b = 0, 1
    while a <n:
        print(a)
        a, b = b, a+b

fib(2 ** 3000)
