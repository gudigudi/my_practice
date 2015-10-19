#!/usr/bin/env python
# encoding: utf-8

def changer(a, b):
    a = 2
    b[0] = 'spam'

X = 1
L = [1, 2]
changer(X, L)
print(X, L)
