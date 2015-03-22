<?php
$a = 1;
$b = 2;

function Sum()
{
    global $a, $b;
    $b += $a;
}

Sum();
echo $b;
?>
