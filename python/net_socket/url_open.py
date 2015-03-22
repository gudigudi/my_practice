#!/usr/bin/env python
# encoding: utf-8

from urllib import urlopen

webpage = urlopen('http://www.python.org')
for line in webpage:
    print(line)
