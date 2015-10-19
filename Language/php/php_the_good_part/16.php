<?php
$myArray = array('first' => 1,
                 'second' => 2,
                 'fname' => "Peter",
                 'initial' => "B",
                 'lname' => "MacIntyre");

echo $myArray['fname'] . " " .
     $myArray['initial'] . " " .
     $myArray['lname'] . "\n";

$myArray[] = "555-5678";
var_dump($myArray);

?>
