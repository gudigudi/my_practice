#!/usr/bin/env python
# encoding: utf-8

import redis
redis = redis.Redis(host='localhost', port=6379, db=0)
print(redis.smembers('circle:jdoe:soccer'))
print(redis.sadd('circle:jdoe:soccer', 'user:fred'))
print(redis.smembers('circle:jdoe:soccer'))

