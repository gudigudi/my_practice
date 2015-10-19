#!/usr/bin/env python3
# encoding: utf-8

s = input("enter an integer:")
try:
    i = int(s)
    print("valid integer entered:", i)
except ValueError as err:
    print(err)
