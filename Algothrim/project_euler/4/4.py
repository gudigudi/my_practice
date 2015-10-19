#!/usr/bin/env python3
# encoding:utf-8

import math

def palindromic(num):
    i = 0
    while num != 0:
        i = 10 * i + num % 10
        print(i)
        num = num // 10
    return i

isFound = False
nums = reversed(range(100, 1000))

for i in nums:
    for j in nums:
        if i*j == int(math.floor(palindromic(i*j))):
            #print("the num is:", i*j)
            isFound = True
            break
    if isFound == True:
        break



