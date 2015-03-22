#!/usr/bin/env python
# encoding: utf-8

# file write
f = open('somefile.txt', 'w')
f.write('Hello, ')
f.write('World!')
f.close()

# file read
f = open('somefile.txt', 'r')
for line in f.readlines():
    print(line)
