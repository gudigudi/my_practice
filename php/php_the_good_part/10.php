<?php
$string = "The quick brown fox jumps over the lazy dog ";
$needle = "BROWN fox";

echo "strstr: ";
var_dump( strstr($string, $needle) );
echo "\n";

echo "stristr: ";
var_dump( stristr($string, $needle) );
echo "\n";

$needle = "the";
echo "strstr: ";
var_dump( strstr($string, $needle) );
echo "\n";

echo "stristr: ";
var_dump( stristr($string, $needle) );
echo "\n";

?>
