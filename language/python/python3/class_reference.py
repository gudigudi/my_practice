#!/usr/bin/env python3
# encoding: utf-8

class B(object):
    a = 23
    b = 45
    def f(self):
        print("method f in class B")
    def g(self):
        print("method g in class B")

class C(B):
    b = 67
    c = 89
    d = 123
    def g(self):
        print("method g in class C")
    def h(self):
        print("method h in class C")

x = C()
x.d = 77
x.e = 88
print(x.__str__)
