#!/usr/bin/env python
# encoding: utf-8

import os

if os.path.isdir("/tmp"):
    print("/tmp is a directory")
else:
    print("/tmp is not a directory")
