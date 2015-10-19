#!/usr/bin/env python
# encoding: utf-8

def myformat(fmt, args): return fmt % args

print(myformat('%s %s', (88, 99)))
print(str.format('{} {}', 88, 99))
