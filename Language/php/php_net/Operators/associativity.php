<?php
$a = 3 * 3 % 5;  // (3 * 3) % 5 = 4
// ternary operator asscciativity differs from C/C++
$a = true ? 0 : true ? 1 : 2;  // (true ? 0 " true) ? 1 : 2 = 2

$a = 1;
$a = 2;
$a = $b += 3;  // $a = ($b += 3); -> $a = 5, $b = 5
?>
