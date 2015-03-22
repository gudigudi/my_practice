#!/usr/bin/env python
# encoding: utf-8

from django.conf.urls import url
from django.http import HttpResponse

def index(request):
    return HttpResponse('Hello World')

urlpatterns = (url(r'^$', index),)

