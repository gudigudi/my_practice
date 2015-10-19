#!/usr/bin/env python
# encoding: utf-8

# tail in Unix
import time

def tail(f):
    f.seek(0, 2)  # move to EOF
    while True:
        line = f.readline()
        if not line:
            time.sleep(0.1)
            continue
        yield line



