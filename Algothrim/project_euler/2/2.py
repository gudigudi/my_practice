#!/usr/bin/env python3
# encoding: utf-8

a = 1
b = 2
sum_num = a+b

while b < 4000000:
    tmp_b = b
    b = a + tmp_b
    a = tmp_b
    sum_num += b

print("sum is:", sum_num)

