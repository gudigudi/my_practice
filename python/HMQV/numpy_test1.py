#!/usr/bin/env python3
# encoding: utf-8

import time, numpy

start = time.clock()

n = 2000000
prime = numpy.arange(n+1, dtype=numpy.uint6 4)
r = int(numpy.sqrt(n))

for j in range(2, r+1):
    if prime[j] != 0:
        prime[j*j:n+1:j] = 0

print(numpy.int64(prime.sum() - 1))

end = time.clock()
print("time: ", end - start)


