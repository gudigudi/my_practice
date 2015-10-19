#!/usr/bin/env python3
# encoding: utf-8

def write_multiple_items(file, separator, *args):
    file.write(separator.join(args))

def concat(*args, sep="/"):
    return sep.join(args)

print(concat("earth", "mars", "venus"))
print(concat("earth", "mars", "venus", sep="."))

def parrot(voltage, state='a stiff', action='voom'):
    print("-- This parrot wouldn\'t", action, end=' ')
    print("if you put", voltage, "volts through it.", end=' ')
    print("E\'s", state, "!")

d = {"voltage" : "four million",
     "state"   : "bleedin",
     "action"  : "VOOM"}
parrot(**d)



