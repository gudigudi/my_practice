<?php
// classa.inc

class A
{
    public $one = 1;

    public function show_one()
    {
        echo $this->one;
    }
}

// page1.php
include("classa.inc");

$a = new A;
$s = serialize($a);
// store $s somewhere where page2.php can find it.
find_put_contents('store', $s);


?>
