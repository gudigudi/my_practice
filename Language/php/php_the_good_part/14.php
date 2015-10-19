<?php
$string = "The <strong>quick</strong> brown fox <a href='jumping.php'>jumps</a> over the lazy dog";

echo htmlentities($string) . "\n";

echo html_entity_decode($string);

?>
