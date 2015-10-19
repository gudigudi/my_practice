#!/usr/bin/env python
# encoding: utf-8

def print_matches(matchtext):
    print("Looking for", matchtext)
    while True:
        line = (yield)
        if matchtext in line:
            print(line)

matcher = print_matches("python")
matcher.next()

