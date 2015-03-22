#!/usr/bin/env python3
# encoding: utf-8
"""
验证HMQV协议:
    1. 将原来的 ** 转换为pow()
    2. 使用gmpy2扩展库，调用C语言gmp模块
"""

def str_to_intStr(oldstr):
    """
    将含有非数字元素的字符串转换为数字
    1. 将含有非数字元素的字符串的每一个字符串转换为数字
    2. 将每个数字转换为字符串，并串联所有数字字符串为一个新的字符串，
       这个新的字符串中只有数字元素
    3. 将新的字符串转换为数字
    """
    newstr = ""
    for c in oldstr:
        newstr += str(ord(c))
    return sttr(newstr)

import hashlib

from gmpy2 import *

test_num = 2

g = mpz(test_num)    # A、B共有的函数
a = mpz(test_num)    # A的私钥
b = mpz(test_num)    # B的私钥
x = mpz(test_num)
y = mpz(test_num)
A = pow(g, a)   # A的私钥的计算值
B = pow(g, b)   # B的私钥的计算值
X = pow(g, x)
Y = pow(g, y)

A_addr = "Alice"
B_addr = "Bob"
# 使用sha384()来进行hash运算

d = str_to_intStr(str(hashlib.sha384((A_addr + x).encode('utf-8')).hexdigest()))
e = str_to_intStr(str(hashlib.sha384((B_addr + y).encode('utf-8')).hexdigest()))

S1 = add(x, mul(a, e))
S2 = add(y, mul(b, d))
Kaa = pow(B, d)
Kbb = pow(A, e)
Ka = pow( mul(Kaa, Y), S1 )
Kb = pow( mul(Kbb, X), S2 )
if Ka == Kb:
    print("equal!")
