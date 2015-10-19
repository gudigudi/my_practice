#!/usr/bin/env python3
# encoding: utf-8

class C6(object):
    def __init__(self, n):
        self.x = n
    def hello(self):
        print("hello")

another = C6(42)
another.hello()
print(another.x)
print(another.__class__)

