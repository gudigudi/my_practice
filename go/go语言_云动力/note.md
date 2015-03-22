### defer ###
defer: 压后，当defer所在函数返回时，调用defer注册的函数。

defer注册的函数按照后进先出的次序依次执行。

### panic & recover ###
golang提供两个内置函数来处理运行时的程序错误: 
* panic结束函数的执行并报告一个错误信息;
* recover恢复被panic的函数

### map ###
使用delete(map, key)来删除映射map的key键单元

### channel ###
channel类型的运算只有三种: send(<-var), receive(var =<-var2 / var<-0), select 

### select ###
select和switch语句类似, 但select的每个分支代表的是通信操作. 
select随机地选择一个没有阻塞的程道.

### sync ###
Go语言的同步包sync另外提供了各种Mutex, Cond, WaitGroup, 能帮助我们更方便地使用
底层的内存共享机制.

### 云计算 ###
云计算的特征:
1. 信息高度集中在运营商的数据中心
2. 用户无法也无需关心自己信息的数据中心
3. 使用唯一的通信标准保障互联互通
4. 终端廉价, 随手可得, 容易使用
5. 按信息, 以及软硬件的使用收费

Bigtable, Paxos

### 标准包 ###
各种数据类型对应的动词

| 数据类型 | 对应动词
| ---- |
| 整数 |
| b    | binary 2进制整数
| o    | octal 8进制整数
| d    | decimal 10进制整数
| x    | hex 小写16进制整数
| X    | heX 大写16进制整数
| 浮点数和复数部分 |
| b    | binary 浮点数二进制
| e    | exponent 小写e浮点计数法, 例如-1234.56e+78
| E    | exponent 大写E浮点计数法, 例如-1234.56E+78
| f    | float 10进制浮点数, 例如-1234.56
| g    | general, 选择f或者e输出较短的格式
| G    | General, 选择f或者E输出较短的格式
| 字符 |
| c    | character 字符
| q    | quote 单引号括起的字符
| U    | Unicode格式的字符, 就是U+%04X
| 字符串或者字节切片 |
| s    | string或slice不加转义的字符串或者字节切片
| q    | quote 双括号括起的字符串
| x    | hex 小写16进制, 每个字节两个字符
| X    | heX 大写16进制, 每个字节连个字符
| 指针 |
| p    | pointer 由0x开始的16进制整数, 代表内存地址
| 布尔 |
| t    | true或者false 
| 通用 |
| v    | value值的通用格式 
| #v   | #value, Go语法表示的值
| T    | Type Go语法表示的类型
| %    | 表示%, 所以%%是一个%

