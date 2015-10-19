<?php
$juice = array("apple", "orange", "koolaid1" => "purple");

echo "He drank some $juice[0] juice.".PHP_EOL;
echo "He drank some $juice[1] juice.".PHP_EOL;
echo "He drank some $juice[koolaid1] juice.".PHP_EOL;

class people
{
    public $john = "John Smith";
    public $jane = "Jane Smith";
    public $robert = "Robert Paulsen";
    public $smith = "Smith";
}

$people = new people();

echo "$people->john drank some $juices[0] juices.".PHP_EOL;
echo "$people->john then said hello to $people->robert.".PHP_EOL;
echo "$people->john's wife greeted $people->rebert.".PHP_EOL;
echo "$people->robert greeted the two $people->smiths.";
?>
