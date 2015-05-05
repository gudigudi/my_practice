#!/usr/bin/env python
# encoding: utf-8

str1 = "I am a student."
l = []
for item in str1.split():
    l.append(item)

str2 = ""
for item in range(0, len(l)):
    str2 += l[len(l)-1-item] + " "

print(str2)
