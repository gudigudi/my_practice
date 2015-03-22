<?php

function MyList($first, $second, $third) {
    echo "here is first: " . $first . "<br />";
    echo "here is second: " . $second . "<br />";
    echo "here is third: " . $third . "<br />";
}

function AddThese($first, $second, $third) {
    $answer = $first + $second + $third;
    return $answer;
}

MyList("Peter", "Chris", "Dean");
echo "<br /><br />";

$first = 5;
$second = 34;
$third = 237;
$math = AddThese($first, $second, $third);
echo "$first, $second, and $third add up to: " . $math;

?>
