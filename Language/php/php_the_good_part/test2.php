<?php
$firstname = "Peter";    # assigned by value
$fname =  &$firstname;
// $firstname is assigned to $fname by reference.
echo $fname . "<br />";  # Peter is displayed
$fname = "Dawn";         # change referenced value
echo $firstname . "<br />";  // Dawn is displayed, not Peter,
                             // because of the "by reference"
?>
