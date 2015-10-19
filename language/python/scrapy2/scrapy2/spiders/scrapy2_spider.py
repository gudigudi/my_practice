import scrapy

class ScrapySpider(scrapy.Spider):
    name = 'zhihu'
    allowed_domains = ["zhihu.com"]
    start_urls = [
        "http://www.zhihu.com/explore"
    ]

    def parse(self, response):
        filename = response.url.split("/")[-2]
        with open(filename, 'wb') as f:
            f.write(response.body)