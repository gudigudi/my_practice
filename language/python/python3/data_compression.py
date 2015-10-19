#!/usr/bin/env python3
# encoding: utf-8

import zlib

s = b'witch which has which witches wrist watch'
print(len(s))

t = zlib.compress(s)
print(t)
print(len(t))

print(zlib.decompress(t))
print(zlib.crc32(s))
