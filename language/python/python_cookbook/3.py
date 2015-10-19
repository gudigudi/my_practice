#!/usr/bin/env python
# encoding: utf-8

thestring = "hej"
print(thestring.ljust(20))
print(thestring.rjust(20))
print(thestring.center(20))
print(thestring.center(20, "+"))

string2 = "   hej   "
print(string2)
print(string2.lstrip())
print(string2.rstrip())
print(string2.strip())
