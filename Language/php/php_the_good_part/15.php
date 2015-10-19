<?php
$string = "The quick brown fox jumps over the lazy dog";
echo str_shuffle($string) . "\n";
echo md5($string) . "\n";
echo md5(str_shuffle($string)) . "\n";


?>
