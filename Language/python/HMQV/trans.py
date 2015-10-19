#!/usr/bin/env python3
# encoding: utf-8

def str_to_intStr(nonstr):
    newStr = ""
    for c in nonstr:
        newStr += str(ord(c))
    return newStr

s = "hello"
print(str_to_intStr(s))

s2 = "你好"
for i in s2:
    print(ord(i))
