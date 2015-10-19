#!/usr/bin/env python3
# encoding: utf-8

import re

line = "Cats are smarter than dogs"

matchObj = re.match( r'(.*) are (.*?) .*', line, re.M|re.I )

if matchObj:
    print(matchObj.gr)
