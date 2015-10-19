<?php
$string = "The <strong>quick</strong> brown fox <a href='jumping.php'>jumps</a>
over the lazy dog";
echo $string . "\n";
echo strip_tags($string) . "\n";
echo strip_tags($string, '<strong>');

?>
