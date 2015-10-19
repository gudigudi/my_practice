#!/usr/bin/env python3
# encoding: utf-8

knights = {'gallahad' : 'the pure',
           'robin'    : 'the brave'}

for k, v in knights.items():
    print(k, v)

for i, v in enumerate(['tic', 'tac', 'toe']):
    print(i, v)

questions = ['name', 'quest', 'favorite color']
answers   = ['lancelot', 'the holy grail', 'blue']
for q, a in zip(questions, answers):
    print('What is your {0}? It is {1}'.format(q, a))

words = ['cat', 'window', 'defenestrate']
for w in words[:]:      # Loop over a slice copy of the entire list.
    if len(w) > 6:
        words.insert(0, w)
print(words)

string1, string2, string3 = '', 'Trondheim', 'Hammer Dance'
non_null = string1 or string2 or string3
print(non_null)


