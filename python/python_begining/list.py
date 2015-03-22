#!/usr/bin/env python3
# encoding: utf-8

print(list('Hello'))

print('list extend')
a = [1, 2, 3]
b = [4, 5, 6]
a.extend(b)
print(a)

print('list sort')
x = [4, 6, 2, 1, 7, 9]
y = x[:]
y.sort()
print('Before sort:\n', x)
print('After sort:\n', y)
