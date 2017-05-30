### URL ###
**URL**基本格式:  
`scheme://host[:port#]/path/.../[?query-string][#auchor]`  
scheme: 协议(http,https,ftp)  
host: IP address or domain name of the server  
port#: HTTP server use port 80, usually ignore it. If you use other port, you should show it  
path: the path to the resource you want(html, css, js and others)  
query-string: the data sended to http server  
anchor : ...  

### DNS ### 
1. hosts file in the OS
2. DNS server 

### HTTP ###

**Request**:

```
GET /domains/example/ HTTP/1.1 
Host: www.iana.org
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/39.0.2171.65 Chrome/39.0.2171.65 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Encoding：gzip,deflate,sdch      //是否支持流压缩
Accept-Charset：UTF-8,*;q=0.5       //客户端字符编码集
//空行,用于分割请求头和消息体                                                                                          
//消息体,请求资源参数,例如POST传递的参数
```

HTTP协议请求方法:
* GET
* POST
* PUT
* DELETE

**Response**:
```
HTTP/1.1 200 OK                     //状态行
Server: nginx/1.0.8                 //服务器使用的WEB软件名及版本
Date:Date: Tue, 30 Oct 2012 04:14:25 GMT        //发送时间                                                                                                  
Content-Type: text/html             //服务器发送信息的类型
Transfer-Encoding: chunked          //表示发送HTTP包是分段发的
Connection: keep-alive              //保持连接状态
Content-Length: 90                  //主体内容长度
//空行 用来分割消息头和主体
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"... //消息体
```

**HTTP状态码**:
* 1XX  提示信息         - 表示请求已被成功接收，继续处理
* 2XX  成功             - 表示请求已被成功接收，理解，接受
* 3XX  重定向       - 要完成请求必须进行更进一步的处理
* 4XX  客户端错误   - 请求有语法错误或请求无法实现
* 5XX  服务器端错误     - 服务器未能实现合法的请求
                                                                                 

