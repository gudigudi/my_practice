#!/usr/bin/env python
# encoding: utf-8

import os

website_domain = "muzi.info"
filename = "whois.dat"
os.system("whois " + website_domain + " > " + filename)

file = open(filename)
info = {}
for line in file.readlines():
    l = line.split(":")
    if len(l) == 2:
        info[l[0]] = l[1]
    elif len(l) == 4:
        info[l[0]] = l[1]+":"+l[2]+":"+l[3]
    else:
        info[l[0]] = None

for i in info.items():
    print(i)
