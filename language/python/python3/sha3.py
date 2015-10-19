#!/usr/bin/env python3
# encoding: utf-8

import hashlib


for i in range(10):
    m1 = hashlib.sha1()
    m1.update( str(i).encode("utf-8") )
    m2 = hashlib.sha224()
    m2.update( str(i).encode("utf-8") )
    m3 = hashlib.sha256()
    m3.update( str(i).encode("utf-8") )
    m4 = hashlib.sha384()
    m4.update( str(i).encode("utf-8") )
    m5 = hashlib.sha512()
    m5.update( str(i).encode("utf-8") )
    m0 = hashlib.md5()
    m0.update( str(i).encode("utf-8") )

    print(i, m1.hexdigest())
    print(i, m2.hexdigest())
    print(i, m3.hexdigest())
    print(i, m4.hexdigest())
    print(i, m5.hexdigest())
    print(i, m0.hexdigest())
