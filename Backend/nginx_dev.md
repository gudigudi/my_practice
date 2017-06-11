## 简介

#### 代码结构
* auto: 编译脚本
* src
  * core:基础数据结构和函数,字符串/数组/日志/内存池等
  * event:事件机制核心模块
    * modules:具体事件机制模块:epoll,kqueue,select等
  * http:HTTP核心模块和公共代码
    * modules:其他HTTP模块
    * v2:HTTP/2模块
  * mail:邮件协议模块
  * os:平台相关代码
    * unix
    * win32
  * stream:流模块

#### 头文件
每个nginx文件都应该在开头包含如下两个头文件
```c
#include <ngx_config.h>
#include <ngx_core.h>
```
除此之外,HTTP相关代码还要包含
```c
#include <ngx_http.h>
```
邮件模块的代码应该包含:
```c
#include <ngx_mail.h>
```
Stream模块的代码应该包含:
```c
#include <ngx_stream.h>
```

#### 整数
* ngx_int_t:intptr_t
* ngx_uint_t:uintptr_t

#### 常用返回值
* NGX_OK:处理成功
* NGX_ERROR:处理失败
* NGX_AGAIN:处理未完成,函数需要被再次调用
* NGX_DECLNED:处理被拒绝,例如相关功能在配置文件中被关闭
* NGX_BUSY:资源不可用
* NGX_DONE:处理完成或者在他处继续处理
* NGX_ABORT:函数终止

#### 错误处理
为了获取最近一次系统错误码，nginx提供了ngx_errno宏。该宏被映射到了POSIX平台的errno变量上，而在Windows平台中，则变为对GetLastError()的函数调用。为了获取最近一次socket错误码，nginx提供了ngx_socket_errno宏。同样，在POSIX平台上该宏被映射为errno变量，而在Windows环境中则是对WSAGetLastError()进行调用。考虑到对性能的影响，ngx_errno和ngx_socket_errno不应该被连续访问。如果有连续、频繁访问的需要，则应该将错误码的值存储到类型为ngx_err_t的本地变量中，然后使用本地变量进行访问。如果需要设置错误码，可以使用ngx_set_errno(errno)和ngx_set_socket_errno(errno)这两个宏。

ngx_errno和ngx_socket_errno变量可以在调用日志相关函数ngx_log_error()和ngx_log_debugX()的时候使用，这样具体的错误文本就会被添加到日志输出中。

一个使用ngx_errno的例子：
```c
void
ngx_my_kill(ngx_pid_t pid, ngx_log_t *log, int signo)
{
    ngx_err_t  err;

    if (kill(pid, signo) == -1) {
        err = ngx_errno;

        ngx_log_error(NGX_LOG_ALERT, log, err, "kill(%P, %d) failed", pid, signo);

        if (err == NGX_ESRCH) {
            return 2;
        }

        return 1;
    }

    return 0;
}
```

## 字符串

#### 概述
nginx字符串类型ngx_str_t的定义如下所示：
```c
typedef struct {
    size_t      len;
    u_char     *data;
} ngx_str_t;
```

结构体成员len存放字符串的长度，成员data指向字符串本身数据。在ngx_str_t中存放的字符串，对于超出len长度的部分可以是NULL结尾（'\0'——译者注），也可以不是。在大多数情况是不以NULL结尾的。然而，在nginx的某些代码中（例如解析配置的时候），ngx_str_t中的字符串是以NULL结尾的，这种情况会使得字符串比较变得更加简单，也使得使用系统调用的时候更加容易。

nginx提供了一系列关于字符串处理的函数。它们在src/core/ngx_string.h文件中定义。其中的一部分就是对C库中字符串函数的封装：
* ngx_strcmp()
* ngx_strncmp()
* ngx_strstr()
* ngx_strlen()
* ngx_strchr()
* ngx_memcmp()
* ngx_memset()
* ngx_memcpy()
* ngx_memmove()
* ngx_memzero():内存清0
* ngx_cpymem():和ngx_memcpy()行为类似，不同的是该函数返回的是copy后的最终目的地址，这在需要连续拼接多个字符串的场景下很方便。
* ngx_movemem():和ngx_memmove()的行为类似，不同的是该函数返回的是move后的最终目的地址。
* ngx_strlchr():在字符串中查找一个特定字符，字符串由两个指针界定。
* ngx_tolower()
* ngx_toupper()
* ngx_strlow()
* ngx_strcasecmp()
* ngx_strncasecmp()

#### 格式化
nginx提供了一些格式化字符串的函数。以下这些函数支持nginx特有的类型：
* ngx_sprintf(buf, fmt, ...)
* ngx_snprintf(buf, max, fmt, ...)
* ngx_slpintf(buf, last, fmt, ...)
* ngx_vslprint(buf, last, fmt, args)
* ngx_vsnprint(buf, max, fmt, args)

#### 数值转换
nginx实现了若干用于数值转换的函数：
* ngx_atoi(line, n) — 将一个指定长度的字符串转换为一个正整数，类型为ngx_int_t。出错返回NGX_ERROR。
* ngx_atosz(line, n) — 同上，转换类型为ssize_t
* ngx_atoof(line, n) — 同上，转换类型为off_t
* ngx_atotm(line, n) — 同上，转换类型为time_t
* ngx_atofp(line, n, point) — 将一个固定长度的定点小数字符串转换为ngx_int_t类型的正整数。转换结果会左移point指定的10进制位数。字符串中的定点小数不能含有多过point参数指定的小数位。出错返回NGX_ERROR。举例：ngx_atofp("10.5", 4, 2) 返回1050
* ngx_hextoi(line, n) — 将表示16进制正整数的字符串转换为ngx_int_t类型的整数。出错返回NGX_ERROR。

#### 正则表达式
>PCRE库的封装

## 时间

## 容器
* 数组
* 列表
* 队列
* 红黑树
* 哈希

## 内容管理
* 堆
* 内存池
* 共享内容

## 日志

## 周期

## 缓冲

## 网络
* 连接

## 事件
* 事件
* I/O事件
* 定时器事件
* 延时事件
* 遍历事件

## 进程

## 线程

## 模块
* 添加新模块
* 核心模块
* 配置指令

## HTTP
* 连接
* 请求
* 配置
* 阶段
* 变量
* 复杂值
* 请求重定向
* 子请求
* 请求结束
* 请求体
* 响应
* 响应体
* 响应体过滤
* 构建过滤模块
* 缓冲复用
* 负载均衡
