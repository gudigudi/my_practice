#!/usr/bin/env python
# encoding: utf-8

# views.py (the business logic)

from django.shortcuts import render_to_response
from models import Book

def latest_books(request):
    book_list = Book.obj
