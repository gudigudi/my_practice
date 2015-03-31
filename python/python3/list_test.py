#!/usr/bin/env python
# encoding: utf-8

M = [[1, 2, 3],
     [4, 5, 6],
     [7, 8, 9]]
print(M)
print(M[1])
print(M[1][2])

col2 = [row[1] for row in M]
print(col2)
