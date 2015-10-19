#!/usr/bin/env python3
# encoding: utf-8

num = 600851475143
i   = 2

while num % i != 0:
    i += 1

largest_prime_factor = int(num / i)
print("largest_prime_factor is:", largest_prime_factor)

