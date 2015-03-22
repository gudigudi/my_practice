#!/usr/bin/env python
# encoding: utf-8

import MySQLdb

print("Content-Type: text/html\n")
print("<html><head><title>Books</title></head>")
print("<body>")
print("<h1>Books</h1>")
print("<ul>")

connection = MySQLdb.connect("me", )
