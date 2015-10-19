<?php
$grades = array(87, 88, 98, 74, 56, 94, 67, 98, 49);

var_dump($grades);
echo "\n";
$uniqueGrades = array_unique($grades);
var_dump($uniqueGrades);
echo "\n";

if (in_array(49, $grades)) {
    echo "there is a 49 in here and it is at element: " . array_search(49, $grades);
} else {
    echo "no 49s here";
}
echo "\n";
shuffle($grades);
var_dump($grades);
echo "\n";
?>
