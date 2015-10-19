<?php
$firstname = "Peter";       # 分配值给变量
$fname     = &$firstname;   # $fname是对$firstname的引用
echo $fname . "<br />";     # 显示内容Peter
$fname = "Dawn";            # 更改内容变量的内容
echo $firstname . "<br />"; # 显示为Dawn而不是Peter，因为是引用。
?>
