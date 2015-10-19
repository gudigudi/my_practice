<?php
function show_stuff() {
    $secondName = "Beck";
    echo "inside show_stuff: " . $firstname . " " . $secondName;
}
$firstname = "peter";       # 除了函数内部，此变量全局可见
echo $firstname . "<br />"; # 显示peter
show_stuff();               # 只会显示Beck，因为$firstname不在函数的作用域里。
echo "Outside function " . $secondName;
                            # 仅仅是在函数show_stuff中有定义，
                            # 所以此处无法访问，
                            # 这里不会显示它的内容
?>
