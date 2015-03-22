#!/usr/bin/env python
# encoding: utf-8

import requests

r = requests.get('http://github.com/timeline.json')

for i in r:
    print(i)
