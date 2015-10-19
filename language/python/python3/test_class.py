#!/usr/bin/env python
# encoding: utf-8

__metaclass__ = type # define new class

class Person:

    def setName(self, name):
        self.name = name

    def getName(self):
        return self.name

    def greet(self):
        print("Hello, world! I'm %s." % self.name)

foo = Person()
bar = Person()
foo.setName('Luke Skywalker')
bar.setName('Anakin Skywalker')
foo.greet()
bar.greet()
