#!/usr/bin/env python
# encoding: utf-8

class User:
    def __init___(self, user_id):
        self.user_id = user_id

    def __repr__(self):
        return 'User({})'.format(self.user_id)

def sort_notcompare():
    users = [User(23), User(3), User(99)]
    print(users)
    print(sorted(users, key=lambda u: u.user_id))

if __name__ == '__main__':
    sort_notcompare()
