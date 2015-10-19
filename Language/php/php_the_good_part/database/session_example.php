<?php
session_start();

if ($_POST['posted'] && $_POST['email'] != "") {
    $folder = "surveys/" . $strtolower($_POST['email']);
    // 路径信息保存到session里
    $_SESSION['folder'] = $folder;
    if (!file_exists($folder)) {
        // 建立目录，添加空文件
        mkdir($folder);
    }
    header( "Location: page1.php" );
} else { ?>
    <html>
    <head>
        <title>File & folder - On-line Survey</title>
    </head>
    <body bgcolor="#FFFFFF" text="#000000">
        <h2>Survey Form </h2>
        <p>Please enter your e-mail address to start recording your comments</p>
        <form action="<?= $PHP_SELF ?>" method="post">
            <input type="hidden" name="posted" value="1"/>
            <p>e-mail address: <input type="text" name="email" size="45"/></p>
            <br/><br/>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    </body>
    </html>

}

<?php } ?>
