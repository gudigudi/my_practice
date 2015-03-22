#!/usr/bin/env python3
# encoding: utf-8

table = {'Sjoerd' : 4127,
         'Jack'   : 4098,
         'Dcab'   : 7678}
for name, phone in table.items():
    print('{0:10} ===> {1:10}'.format(name, phone))

print('Jack: {Jack:d}; Sjoerd {Sjoerd:d}; Dcab: {Dcab:d}'.format(**table))

