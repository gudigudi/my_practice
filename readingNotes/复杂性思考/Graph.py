#!/usr/bin/env python3
# encoding: utf-8

class Graph(dict):
    def __init__(self, vs=[], es=[]):
        """ create a new graph. (vs)is a list of vertices;
        (es) is a list of edges.
        """
        for v in vs:
            self.add_vertex(v)

        for e in es:
            self.add_edge(e)

    def add_vertex(self, v):
        """ add (v) to the graph
        """
        self[v] = {}

    def add_edge(self, e):
        """ add (e) to the graph by adding an entry in both directions.
        If there is already an edge connecting these Vertices, the new
        edge replaces it.
        """
        v, w = e
        self[v][w] = e
        self[w][v] = e

class Vertex(object):
    def __init__(self, label=''):
        self.label = label
    def __repr__(self):
        return 'Vertex(%s)' % repr(self.label)
    __str__ = __repr__

class Edge(tuple):
    def __new__(cls, e1, e2):
        return tuple.__new__(cls, (e1, e2))
    def __repr__(self):
        return 'Edge(%s, %s)' % (repr(self[0]), repr(self[1]))
    __str__ = __repr__
