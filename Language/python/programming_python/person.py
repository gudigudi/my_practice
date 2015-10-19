#!/usr/bin/env python
# encoding: utf-8

class Person:
    def __init__(self, name, age, pay=0, job=None):
        self.name = name
        self.age  = age
        self.pay  = pay
        self.job  = job
    def lastName(self):
        return self.name.split()[-1]
    def giveRaive(self, percent):
        self.pay *= (1.0 + percent)
    def __str__(self):
        return '<%s => %s>' % (self.__class__.__name__, self.name)

if __name__ == '__main__':
    bob = Person('Bob Smith', 42, 30000, 'software')
    sue = Person('Sue Jones', 45, 40000, 'hardware')
    print(bob.name, sue.pay)

    print(bob.lastName())
    sue.giveRaive(.10)
    print(sue.pay)
