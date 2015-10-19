#!/usr/bin/env python3
# encoding: utf-8

class Dog:
    tricks = []

    def __init__(self, name):
        self.name = name    # instance variable unique to each instance
    def add_trick(self, trick):
        self.tricks.append(trick)

d = Dog('Fido')
e = Dog('Buddy')
d.add_trick('roll over')
e.add_trick('play dead')
print(d.tricks)

def f1(self, x, y):
    return min(x, x+y)

class C:
    f = f1
    def g(self):
        return 'hello world'
    h = g

class Bag:
    def __init__(self):
        self.data = []
    def add(self, x):
        self.data.append(x)
    def addwice(self, x):
        self.add(x)
        self.add(x)

