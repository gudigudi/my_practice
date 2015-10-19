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
print([row[1] + 1 for row in M])
print([row[1] for row in M if row[1] % 2 == 0])

diag = [M[i][i] for i in [0, 1, 2]]
print(diag)

doubles = [c * 2 for c in 'spam']
print(doubles)

print(list(range(4)))
print(list(range(-6, 7, 2)))
print([[x ** 2, x ** 3] for x in range(4)])
print([[x, x / 2, x * 2] for x in range(-6, 7, 2) if x > 0])

G = (sum(row) for row in M)
print(next(G))
print(next(G))
print(next(G))

print(list(map(sum, M)))
