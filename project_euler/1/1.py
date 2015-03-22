#!/usr/bin/env python3
# encoding: utf-8

a = 3
b = 5
upper_num = 1000
sum_num = 0

for i in range(1, int(upper_num/a)+1):
    if i*a != upper_num:
        sum_num += i*a
for i in range(1, int(upper_num/b)+1):
    if i*b != upper_num:
        sum_num += i*b

print("sum is: ", sum_num)


