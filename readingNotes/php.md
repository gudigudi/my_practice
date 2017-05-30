### Variables ###
使用`$variables`来创建变量

**PHP data types**

segment        | type          | description/example 
-------------- | ------------- | ------------------- 
scalar types   | Boolean       | TRUE/FALSE          
               | Integer       |                     
               | Float(double) |
               | String        | 
compound types | Array         |
               | Object        |
special types  | NULL          |
               | Resource      | stores a reference to functions, databases, files, or other resources outside of PHP

### basic syntax ###
``` php
if ... {
    ...
} elseif ... {
    ...
} else {
    ...
}
```
``` php
switch(value) {
    case value1:
        ...
    case value2:
        ...
    default:
        ... // if nothing else is true, do this
}
```
``` php
while(...) {
    ...
}

do {
    ...
} while(...);

for ( ; ; ) {
    ...
}
```

### web tools ###
**Cookies: $_COOKIE**
``` php
setcookie("String_Data_Information", $data), time()+60*60*24*30); # set the cookie for 30 days
$newData = $_COOKIE["CookieName"];
```

**Sessions: $_SESSION**
``` php
$_SESSION['today'] = $today;
```

**Form: $_GET, $_POST**
``` php
$login = $_GET['login'];
$password = $_POST['password'];
```

**Include and Require**
``` php
include "include.php";
include_once "include.php";
request "request.php";
request_once "request.php";
```

### string ###
```
$string = <<< RightHERE
...
RightHERE;    # 多行字符串
```
**string function**
* ltrim(string)
* rtrim(string)
* trim(string)
* ucfirst(string)
* lcfirst(string)
* str_word_count(string)
* strlen(string)
* strpos(string, "fox")
...

