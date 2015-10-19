<?php
// 正常情形下，这个值将由一个登陆表单提供
$string = 'myPassword';

$salt = 'peter';

$pepper = "MacIntyre";

echo "Here is the original sha1 encryotion: " . sha1($string);
echo "\n";

$salt = sha1($pepper);

$string = $salt . $string . $pepper;
echo "Here is the prepared string about to be further encryoted: " . $string;
echo "\n";
echo "Here is the well seasoned encryotion: " . sha1($string);


