<?php
$a = gmp_init(42);
$b = gmp_init(17);

// PHP-5.6 code:
var_dump($a + $b);
var_dump($a + 17);
var_dump(42 + $b);

?>
