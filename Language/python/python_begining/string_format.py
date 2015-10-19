#!/usr/bin/env python3
# encoding: utf-8

width = input('Please enter width: ')

price_width = 10
item_width  = width - price_width

header_format = '%-*s%*s'
format        = '%-*s%*s'

print('-' * width)

print(header_format % (item_width, 'Item', price_width, 'Price'))

