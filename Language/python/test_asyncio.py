#!/usr/bin/env python3
# encoding: utf-8

import asyncio, re, random, sys, aiohttp

base_url = 'http://play.google.com/store/apps/details?id=%s'

@asyncio.coroutine
def get_body(url: str) -> str:
    response = yield from aiohttp.request('GET', url)
    body = yield from response.read_and_close()
    return body.decode('utf-8')

pattern = re.compile(r'<div class="content" itemprop="numDownloads">(.+?)</div>')
sem = asyncio.Semaphore(100)

def parse_count(content: str) -> int:
    matches = pattern.findall(content)
    if len(matches) < 1:
        return None
    if len(matches) > 1:
        print('multi match:', matches)
    count_str = matches[0].strip()
    begin, end = count_str.split(' - ')
    begin, end = int(begin.replace(',', '')), int(end.replace(',', ''))
    return random.randint(begin, end)

@asyncio.coroutine
def print_count(pn: str) -> str:
    url = base_url % pn
    with (yield from sem):
        try:
            content = yield from get_body(url)
            count = parse_count(content)
            print('{}\t{}'.format(pn, count))
        except Exception as e:
            print(e)

def main():
    pn_file = 'pn.list'
    if len(sys.argv) == 2:
        pn_file = sys.argv[1]
    pns = []
    with open(pn_file) as f:
        for line in f:
            pns.append(line.strip())

    loop = asyncio.get_event_loop()
    tasks = asyncio.wait([print_count(pn) for pn in pns])
    loop.run_until_complete(tasks)

if __name__ == '__main__':
    main()

