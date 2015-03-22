#!/usr/bin/env python
# encoding: utf-8

class Bird:

    def __init__(self):
        self.hangry = True

    def eat(self):
        if self.hangry:
            print("Aaaah...")
            self.hangry = False
        else:
            print("No, thanks!")

b = Bird()
b.eat()
b.eat()

class SongBird(Bird):

    def __init__(self):
        self.sound = 'Squawk!'
        super(SongBird, self).__init__()
    def sing(self):
        print(self.sound)

sb = SongBird()
sb.eat()
sb.sing()
