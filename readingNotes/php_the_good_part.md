### 1. 精粹 ###

定义变量: 

``` php 
<?php 
$name = "hello";
$name2 = &$name;    # name2引用了name的地址，即两者所指向的地址相同
?>
```

定义常量:

PHP使用内置函数define()来定义常量
```
<?php 
define("常量名称", 常量值, [是否大小写不敏感])
?>
```

#### __Web页面交互__

__客户端Cookie__ 

设置cookie 
``` php
<?php 
$data = "this will be placed in the cookie";
setcookie("CookieName", $data);
setcookie("AnotherCookieName", $data, time()+60*60*24*30);
?>
```
获取cookie 
``` php 
<?php
$newData = $_COOKIE["AnotherCookieName"];
?>
```

__服务器端session__

启动session并保存值
``` php
<?php 
session_start();
$today = date("Y-m-d");     // 用YYYY-MM-DD的格式返回今天的日期
$_SESSION["today"] = $today;    // 添加这个日期到session 
$_SESSION["login_name"] = "Peter";  // 添加这个登陆名到session 
?>
```

超全局变量 $_GET和$_POST

超全局数组REQUEST数组，这是一个包含各类请求数据的数组，即$_COOKIE、$_GET、
$_POST

### 2. 函数 ###

php的函数的参数可以设置默认值
``` php 
<?php 
function func($name = "hello") {
    echo $name;
}
?>
```

__include__(代表包含)和__request__(代表必需) 

### 3. 字符串 ###

echo和print的区别:
1. echo支持多个参数
2. print只支持完成一个参数，输出完成后返回1

一种名叫HEREDOC的结构也能够创建字符串，它适用于创建很长的字符串，因此，在程序
中使用它会让程序更易于阅读，HEREDOC使用三个小于号(<)和指定的名称开始，然后是
字符串，另起一行使用指定的名称和分号结尾。
``` php 
<?php 
$string = <<< RightHERE
Lasdklfjdskfjdsdjfka
askdjfkdslf
dskfjadsklffjadfdskfjadsklffjadf 
ajkdfjasdlfsad.
RightHERE;
?>
```

__字符串函数__

字符串__截取__ 

字符串传递到代码里常常会在开头或者末尾包含空格，为确保你的字符串不传输额外的
内容，可使用ltrim或者rtrim函数，如果首尾都有空格，可使用trim。

管理字符的__大小写__
* ucwords($string): 将字符串的每个单词的首字母变大写
* strtoupper($string): 将整个字符串变大写
* strtolower($string): 将整个字符串变小写
* ucfirst($string): 将字符串的首字母变大写
* lcfirst($string): 将字符串的首字母变小写

字符串__查找__
* str_word_count($string): 返回字符串中的单词个数
* strlen($string): 返回字符串的长度
* strstr($string, $needle): 返回子字符串$needle及其之后的字符串，若找不到，返回false
* stristr($string, $needle): 同上，但不区分大小写
* strpos($string, "substring"): 返回substring在string中的位置，若找不到，返回false
* substr($string, position, length): 获得子字符串
* str_replace(oldstring, newstring, $string): 返回替换后的新字符串

字符串__编辑__ 
* strip_tags($string): 返回清除标签的新的字符串 
* strip_tags($string, '<tag>'): 同上，但保留tag标签
* addslashes($string): 添加转义字符
* stripslashes($string): 删除转义字符
* htmlentities($string): 将字符串中的特殊字符转换到原生格式
* html_entity_decode($string): 将字符串的html原生格式转换为特殊字符
* str_shuffle($string): 产生给定字符串的随机重组
* md5($string): 完全打乱一个字符串，返回一个32位长的十六进制字符串的结果，同一个$string产生唯一的md5

### 4. 数组 ###

* 索引数组: 以数字为键的数组
* 关联数组: 以字符串为键的数组

数组可以__动态构建__ 

* array_splice($array, start, length): 剪切数组元素
* unset(key): 删除某个数组元素

__遍历数组__ 

__foreach__结构: 允许任意代码单步访问所提供数组的每一个元素或每组键/值对。
``` php 
<?php 
foreach ($array as key => value) {
  echo "the key is " . $key . " and its value is " . $value . "<br />"; 
}
?>
```

#### 数组函数精选 ####

__数组排序__ 
* sort: 排序
* rsort: 逆向排序
* asort: 比较值(value)
* rsort: 比较值，逆向
* ksort: 比较键(key)，适合关联数组
* rksort: 比较键(key)，适合关联数组，逆向
* usort: 允许使用自定义的特殊条件对数组进行排序

__数学类函数__
* array_sum():
* count():
* array_qnique(): 删除重复元素
* in_array(value, $array): 检测一个值是否在数组中
* array_search(): 查找值
* shuffle($array): 打乱数组中的所有值
* array_rand(): 产生一个随机值
* array_merge(): 将两个数组合并

### 5. 面向对象 ###

魔术方法: 会自动执行的预先定义的方法
* __construct: 魔术方法，构造函数
* __destruct: 魔术方法，析构函数

### 6. 数据库 ###

__连接__数据库:
``` php 
<?php 
$mydb = new mysqli('hostname', 'username', 'userpassword', 'dbname');
?>
```

__取得数据__并__显示__

``` php 
<?php 
$mddb = new mysqli('hostname', 'username', 'userpassword', 'dbname');

$sql = "SELECT * FROM Guests BY lname, fname";

$result = $mydb->query($sql);

while( $row = $result->fetch_assoc() ) {
  echo $row['fname'] . " " . $row['lname'];
  echo " 评论说: " . substr($row['comments'], 0, 150);
  echo "<br />";
}

$result->close();
$mydb->close();
?>
```

__PHP数据对象(PDO)__

PDO拥有许多数据库平台的额外驱动
``` php 
<?php 
$dns = 'mysql:dbname=website;host=localhost';
$myPDO = new PDO($dns, 'username', 'userpassword');

$sql = "INSERT INTO guests (fname, lname, comments) 
VALUEs ('$_POST[fname]', '$_POST[lname]', '$_POST[comments]')";

$result = $myPDO->query($sql);

if ( $result !== False ) {
  echo "store data successful!";
} else {
  echo "fail to store data! ";
}
?>
```

multi_query(): 执行多条SQL语句。

PDO__预处理对象__

你可以通过次方法建立需要重复执行且只有细微差别的SQL语句。
``` php 
<?php 
... 
$statement = $myPDO->prepare('SELECT * FROM Guests ORDER BY ?');
$statement->execute(array('lname'));
?>
```

__低成本__数据管理方案

__SQLite__: PHP默认安装可用，它的所有数据都基于单个文件存储，不需独立的数据库引擎

``` php 
<?php 
$database = new SQLiteDatabase('path_to_sqlite_file');
$sql = "SELECT * FROM Guests";
$database->queryExec($sql);
?>
```

SQLite支持的数据类型

| 数据类型 | 解释 |
| -------- | ---- |
| 文本     | 可以存储NULL，TEXT或BLOB类型的内容，如果传入的是数字，在存储前会被转换成文本 |
| 数字     | 可以存储整数或实数，文本在存储前也会被转换 |
| 整数     | 和数字类型相似，但如果是实数，会先转换成整数，这会影响数据精度 |
| 实数     | 也和数字类型，但会强制将整数作为浮点数存储 |
| 空       | 是个比较杂的类型，并不限制是哪种方式存储，提供什么就存什么，很精确 |

用__文件__替代数据库

通用PHP__文件管理__函数

| 函数名 | 功能描述 |
| ------ | -------- |
| mkdir()           | |
| file_exists()     | 检测指定的文件或目录是否存在 |
| fopen()           | |
| fread()           | |
| flock()           | 为所写的文件添加一个排他锁 |
| fwrite()          | |
| filesize()        | 当读取文件时，用此方法检测总共读了多少字节 |
| fclose()          | |

### 8. PHP周边 ###

介绍3个扩展库: 实现邮件发送或文字短信服务(SMS)、生成PDF(文件)、生成图形数据报表(如饼图、柱状图等)。

__电子邮件/短信生成__

内建邮件函数mail()。 

PHP库PHPMailer： 可以实现发送带附件的内容的功能。

### 9. PHP的安全性 ###

__数据验证__

__转义输出__
* htmlspecialchars($string): 将数据转换成HTML实体
* htmlentities(): 将所有特殊字符转换成HTML实体
* mysql_real_escape_string($string): 将sql语句中的特殊字符串进行转义，避免出错

跨站脚本(__XXS__)和__SQL注入__
* 对输入进行过滤 并且不允许任何分号
* 保证网页用户(你用来访问数据库的标识)没有删除表格的权限

密码__加密__安全
* 将原始数据进行加密

### 10. PHP5.3精粹 ###

__命名空间__
命名空间的作用: 避免不同对象库或函数库的命名冲突

创建命名空间的规则:
1. 命名空间的声明必须在首部，前面不能有任何代码，除了注释 
2. 必须使用关键字namespace 
3. 名字唯一，不可使用PHP关键字
4. 所有其它代码必须包含在命名空间之内

``` php 
<?php
namespace Sample {
  function hello($name) {
    echo "hello " . $name; 
  }

hello( "world" );
}
?>
```

当需要调用命名空间中的函数时，使用"Sample\funcName()"。

__闭包__(匿名函数)
允许建立没有名字的函数，通过将匿名函数分配给一个变量的形式。
``` php 
<?php 
$person_info = function($name, $age, $eyecolor) {
  echo "greetings: " . $name . "\n";
  echo "You are : " . $age  . " years old\n";
  echo "and your eye color iss : " . $eyecolor . "\n\n";
}

$person_info( 'Peter', '43', 'brown');
?>
```


