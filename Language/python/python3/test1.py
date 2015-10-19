#!/usr/bin/env python3
# encoding: utf-8

"""
x, y 互换
"""
x = 6
y = 5
print("x = ", x)
print("y = ", y)
x, y = y, x
print("x = ", x)
print("y = ", y)

"""
字符串连接
"""
nfc = ["Packers", "49ers"]
afc = ["Ravens", "Patriots"]
print(nfc + afc)

"""
数值比较
"""
x = 2
if 3 > x > 1:
    print(x)
if 1 < x > 0:
    print(x)

"""
同时遍历两个列表
"""
nfc = ["Packers", "49ers"]
afc = ["Ravens", "Patriots"]
for teama, teamb in zip(nfc, afc):
    print( teama + " vs. " + teamb )

"""
遍历列表同时得出序号和内容
"""
teams = ["Packers", "49ers", "Ravens", "Patriots"]
for index, team in enumerate( teams ):
    print(index, team)

