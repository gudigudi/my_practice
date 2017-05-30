### 函数
``` bash
function function_name
{
  commands to execute
}
# 或
function_name ()
{
  commands to execute
}
```

### 控制结构
if...then语句
``` bash
if [ test_command ];then
  commands
fi
```
if...then...else语句
``` bash
if [ test_command ];then
  commands
else
  commands
fi
```
if...then...elif...(else)语句
``` bash
if [ test_command ];then
  commands
elif [ test_command ];then
  commands
else
  commands
fi
```
for...in语句
``` bash
for loop_variable in arguments_list;do
  commands
done
```
while语句
``` bash
while test_condition_is_true;do
  commands
done
```
until语句
``` bash
until test_condition_is_true;do
  commands
done
```
case语句
``` bash
case $variable in
match_1)
  commands_to_execute_for_1
  ;;
match_2)
  commands_to_execute_for_2
  ;;
esac
```
### 符号命令
| 命令        | 描述                                              |
| ----------- | ------------------------------------------------- |
| ()          | 在一个子shell脚本里运行括号内的命令               |
| (())        | 在某个shell中对一个变量求值或赋值，并进行数学运算 |
| $(())       | 求括号内表达式的值                                |
| []          | 同test命令                                        |
| <>          | 用于字符串比较                                    |
| $()         | 命令替换                                          |
| 'command'   | 命令替换                                          |

### 命令行参数
命令行参数$1,$2,$3,...$9是位置参数，$0指向实际的命令、程序、shell脚本、或函数，
$1, $2, $3,...$9作为命令的参数。

在一个函数中出现的位置参数$0, $2等由函数本身使用，可能不会在调用函数的shell脚本
环境中出现。变量在函数或shell脚本中的可知范围称为变量的作用域(scope)。

### shift命令
shift命令用于将位置参数向左移动，例如，shift命令使得$2成为$1。

### 特殊参数 $ * 和 $ @

特殊参数 $ * 和 $ @ 允许一次访问所有的命令行参数，定义如下:
* 特殊参数 $ * 指定了所有的命令行参数。
* 特殊参数 $ @ 也指定了所有的命令行参数。
* 特殊参数 " $ * "将整个参数列表，并将其分隔成不同的参数。
* 特殊参数 " $ @ "获取整个参数列表，并将其分隔成不同的参数。

### shell的职责
shell负责确保用户在命令提示符后输入的命令被正确执行。其职责包括:
1. 读取输入并解析命令行
2. 替换特殊字符，比如通配符和历史命令符
3. 设置管道、重定向和后台处理
4. 处理信号
5. 程序执行的相关设置

### 命令类型
命令被执行时，可以是别名、函数、内置命令或磁盘上的一个可执行程序。别名是原有命
令的缩写(昵称)，可用于C、TC、bash和Korn shell。函数是由像独立的例程一样的指令组
织起来的一组命令。别名和函数都在shell的内存空间中定义。内置命令是shell的内置程
序，而可执行程序则在磁盘上。shell用路径变量在磁盘上定位可执行程序。执行命令前，
shell需要创建一个子进程。执行命令前，shell按如下顺序判断命令类型:
* 别名
* 关键字
* 函数
* 内置命令
* 可执行程序
