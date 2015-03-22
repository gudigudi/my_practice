#!/usr/bin/env python
# encoding: utf-8

from tkinter import *
from tkinter.messageBox import showinfo

def reply():
    showinfo(title='popup', message='Button pressed!')

window = Tk()
button = Button(window, text='press', command=reply)
button.pack()
window.mainloop()
