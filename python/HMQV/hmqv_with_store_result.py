#!/usr/bin/env python3
# encoding: utf-8

"""
验证HMQV协议:
    1. 将原来的 ** 转换为pow()
    2. 使用gmpy2扩展库，调用C语言gmp模块
"""

from hashlib import *
from gmpy2 import *

test_num = 100

g = mpz(test_num)    # A、B共有的函数
a = mpz(test_num)    # A的私钥
b = mpz(test_num)    # B的私钥
x = mpz(test_num)
y = mpz(test_num)

A_addr = "Alice"
B_addr = "Bob"

A = pow(g, a)   # A的私钥的计算值
B = pow(g, b)   # B的私钥的计算值
X = pow(g, x)
Y = pow(g, y)


d = 33
e = 43


# 使用sha384()来进行hash运算

# d = int(hashlib.sha384((A_addr + A_x).encode('utf-8')).hexdigest())
# e = int(hashlib.sha384((B_addr + B_y).encode('utf-8')).hexdigest())

S1 = add(x, mul(a, e))
S2 = add(y, mul(b, d))
Kaa = pow(B, d)
Kbb = pow(A, e)
Ka = pow( mul(Kaa, Y), S1 )
Kb = pow( mul(Kbb, X), S2 )
if Ka == Kb:
    print("equal!")
    file = open('data2.txt', 'w')
    file.write(str(Ka))
    file.close()
