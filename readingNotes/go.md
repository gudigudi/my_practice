### 1. 入门 ###

基本类型:
* 布尔型: bool
* 整型: int, uint, int32, int64 
* 浮点型: float32, float64 
* 复数: complex64, complex128 
* 字符串: string 
* 字符类型: rune 
* 错误类型: error

复合类型:
* 指针: pointer 
* 数组: array 
* 切片: slice 
* 字典: map
* 通道: chan 
* 结构体: struct 
* 接口: interface

预定义常量: 
* true: 真
* false: 假
* iota: 初次调用时值为0, 以后每次调用时值加1

预定义函数:
close: 用于关闭channel
delete: 用于在map中删除实例
len和cap: 可用于不同的类型，len用于返回字符串、slice和数组的长度
new: 用于各种类型的内存分配
make: 用于内建类型(map, slice,和channel)的内存分配
copy: 用于复制slice
append: 用于追加slice
panic和recover: 用于异常处理机制(恐慌，恢复)
print和println: 底层打印函数，主要用于调试
complex、real和imag: 用于处理复数

### package ###
包是函数和数据的集合，用package关键字定义一个包。文件名不需要与包名一致。包名的约定是使用小写字符。Go包可以由多个文件组成，但是使用相同的`package <name>`这一行。名称以大写字母起始的是可导出的，可以在包的外部调用。

* 公有函数的名称以大写字母开头
* 私有函数的名称以小写字母开头

根据规则，包名是小写的一个单词; 不应当有下划线或混合大小写。  

**包的文档**
每个包都应该有包注释，在package前的一个注释块。对于多文件包，包注释只需要出现在一个文件前，任意一个文件都可以。包注释应当对包进行介绍，并提供相关于包的整体信息。  

**测试包**
在go中为包编写单元测试应当是一种习惯。编写测试需要包含testing包和程序gotest。两者都有良好的文档。  
go test程序会自动调用所有的测试函数。  

**常用的包**
* fmt: 格式化的I/O函数
* io: 原始的I/O操作界面，对os包这样的原始的I/O进行封装  
* bufio: 缓冲的I/O  
* sort: 对数组和用户定义集合的原始的排序功能  
* strconv: 将字符串转换成基本数据类型，或者从基本数据类型转换为字符串  
* os: 提供了与平台无关的操作系统功能接口。其设计是unix形式的  
* sync: 提供了基本的同步原语，例如互斥锁  
* flag: 实现了命令行解析  
* encoding/json: 实现了编码和解码  
* html/template: 数据驱动的模版，用于生成文本输出，例如HTML  
* net/http: 实现了HTTP请求、响应和URL的解析，并且提供了可扩展的HTTP服务和基本的HTTP客户端  
* unsafe: 包含go程序中数据类型上所有不安全的操作  
* reflect: 实现了运行时反射，允许程序通过抽象类型操作对象。通常用于处理静态类型interface{}的值，并且通过Typeof解析出其动态类型信息，通常会返回一个有接口类型Type的对象  
* os/exec: 执行外部命令
