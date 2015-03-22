<?php
session_start();
$folder = $_SESSION['folder'];
$filename = $folder . "/question1.txt";

$file_handle = fopen($filename, "a+");
// 打开需要读取的文件
// 读取文件中存在的任何内容
$comments = fread($file_handle, filesize($filename));
fclose($file_handle);   // close this handle

if ($_POST['posted']) {
    // 第一次需要建立文件，并将$_POST['question1']中的文字内容保存起来
    $question1 = $_POST['question1'];
    $file_handle = fopen($filename, "w+");
    // 完全改写打开的文件

    if (flock($file_handle, LOCK_EX)) {
        // 设置一个排他锁
        if (fwrite($file_handle, $question1) == FALSE) {
            echo "不能写入文件: ($filename)";
        }
        flock($file_handle, LOCK_UN);   // 释放锁
    }
    // 关闭文件句柄并重定向到下一页?
    fclose($file_handle);
    header( "Location: page2.php" );
} else {
?>
    <html>
    <head>
        <title>File & folder - On-line Survey </title>
    </head>
    <body>
        <table border="0">
            <tr>
                <td>Please enter your response to the following survey question:</td>
            </tr>
            <tr bgcolor="#add8e6">
                <td>What is our</td>
            </tr>
        </table>
    </body>
    </html>

    }

<?php

?>