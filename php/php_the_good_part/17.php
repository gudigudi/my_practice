<?php
$grade = array(87, 88, 98, 74, 56, 94, 67);

$addedGrades = array_sum($grade);
echo "the sum of the provided grades is: $addedGrades \n";
$avgGrades = array_sum($grade) / count($grade);
echo "and the average of these grades is: " . round($avgGrades, 2) . "\n";

?>
