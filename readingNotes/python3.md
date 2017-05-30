### 数据结构 ###
1. list: [1, 2, 3]
2. tuple: (1, 2, 3)
3. set: {'1', '2', '3'}  
4. directory: {'1': python, '2': pypy}

### list的方法 ###
* list.append(x):
* list.extend(L):
* list.insert(i, x)
* list.remove(x)
* list.pop([i])
* list.index(x)
* list.count(x)
* list.sort()
* list.reverse()

**use list as stack**
* list.append(x)
* list.pop()

**use list as queue**
* list.append(x)
* list.remove(x)

使用collections.deque来实现队列
* deque.append(x)
* deque.popleft()

**del语句**  
从list中按给定的索引来删除一个子项

### tuple ###
``` python
t = (1, 2, 3)
```
> 不能给tuple的一个独立的item赋值

### set ###
无序不重复元素的集，基本功能包括关系测试和消除重复元素。集合对象还支持union(联合)，intersection(交), difference(差)和sysmmetric difference(对称差集)等数学运算。
> 必须使用set()来创建集合

### dictionary ###
key:value(键值对)集合
* dict.keys(): 返回一个字典中所有关键字组成的无序列表
* dict.items(): 返回一个字典中关键字和对应值的所有组合
> 可以使用dict()来创建字典  
> 可以使用zip()将两个list进行打包  
> reverse(list): 返回list的逆向形式  
> sorted(list):  返回list的已排序形式  

### 深入条件控制 ###
比较操作符in和not in: 审核值是否在一个区间之内  
比较操作符is和is not: 比较两个对象是否相同  

## 模块 ##
``` python
import module
from module import function
from module import *
```

### 输入和输出 ###
**字符串格式化**
``` python
str.zfill(number) # 填充多个0到str中，知道str的length为number
""" 
str.format() 用于格式化str 
"""
print('{0} and {1}'.format("spam", 'egg'))
print('{0} and {1} and {three}'.format("spam", 'egg', three='333'))
# 格式化
{num:2.4f}.format(num='123.456789')
```
**文件**
``` python
# open file
f = open('/tmp/workfile', 'w')
# read context from file
f.read(size)
f.readline() # 如果返回值为'\n', 则该行为空行
f.readlines() # 返回多行
f.write() # write content to file
```

### 错误和异常 ###
``` python
try :
    ....
except Error:
    ....
finally:
    ....
```
**用户自定义异常**
``` python
class MyError(Exception):  # error class继承自Exception
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)
```

### 类 ###
**作用域**
* global: 将变量引入到全局域
* nonlocal: 指明某个特定的变量为封闭作用域，并重新绑定它
    
**magic 方法**
* `__init__`:    构造方法
* `__iter__`:    迭代方法

**继承**
``` python
class subClass(superClass1, superClass2):
    """docString"""
```

**私有变量**
以一个下划线开头的变量
以两个下划线开头的变量: 表明该变量是该类独有的

**抛出异常**
* raise

### 标准库 ###
**操作系统接口**  
`os`: 提供常用的系统接口函数  
`shutil`: 提供高级接口  

**文件通配符**  
`glob`: 提供一个函数用于从目录通配符搜索中生成文件列表  

**命令行参数**  
`sys`: sys.argv用于获取命令行参数  
`getopt`: 使用Unix`getopt()`函数处理sys.argv  
`argparse`: 处理复杂命令行  

**错误输出重定向和程序终止**  
sys拥有stdin, stdout和stderr属性  
可使用`sys.exit()`进行脚本的定向终止  

**字符串正则匹配**  
`re`

**数学**  
`math`: 为浮点运算提供了对底层C函数库的访问  
`random`: 提供了生成随机数的工具  
> SciPy提供了许多数值计算的模块  

**互联网访问**
`urllib.request`: 处理从urls接收的数据  
`smtplib`: 发送电子邮件  

**日期和时间**  
`datetime`  

**数据压缩**
`zlib, gzip, bz2, zipfile, tarfile`  

**性能度量**  
`timeit`  

**质量控制**  
开发高质量软件的方法之一是为每一个函数开发测试代码，并且在开发过程中经常进行测试。  
`doctest`: 提供了一个工具，扫描模块并根据程序中内嵌的文档字符串执行测试。测试构造如同简单的将它的输出结果剪切并粘贴到文档字符串中。通过用户提供的例子，它发展了文档，允许 doctest 模块确认代码的结果是否与文档一致。  
`unittest`: 在一个独立的文件里提供一个更全面的测试集  

**"瑞士军刀"**  
* `xmlrpc.client`和`xmlrpc.server`: 让远程过程调用变得轻而易举  
* `email`: 管理邮件信息  
* `xml.dom`和`xml.sax`: 为流行的信息交换格式提供支持, `csv`: 在通用数据库格式中直接读写  
* `gettext`, `locale`和`codecs`: 国际化  

