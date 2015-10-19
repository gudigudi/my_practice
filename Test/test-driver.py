#!/usr/bin/env python
# encoding: utf-8

import random
import unittest

class TestSequencFunction(unittest.TestCase):
    def setUp(self):
        self.seq = range(10)

    def testshuffle(self):
        random.shuffle(self.seq)
        self.seq.sort()
        self.assertEqual(self.seq, range(10))

    def testsample(self):
        self.assertRaises(ValueError, random.sample, self.seq, 20)
        for element in random.sample(self.seq, 5):
            self.assert_(element in self.seq)

if __name__ == '__main__':
    unittest.main()
