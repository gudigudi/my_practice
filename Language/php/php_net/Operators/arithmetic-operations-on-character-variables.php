<?php
echo '== Alphabets ==' . PHP_EOL;
$s = 'W';
for ($i = 0; $i < 6; $i++) {
    echo ++$s . PHP_EOL;
}
// Digit characters behave differently
echo '== Digit ==' . PHP_EOL;
$d = 'A8';
for ($i = 0; $i < 6; $i++) {
    echo ++$d . PHP_EOL;
}
$d = 'A08';
for ($i = 0; $i < 6; $i++) {
    echo ++$d . PHP_EOL;
}
?>
