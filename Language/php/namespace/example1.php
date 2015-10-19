<?php
class classname
{
    public function __construct()
    {
        echo __METHOD__, "\n";
    }
}

function funcname()
{
    echo __FUNCTION__, "\n";
}

const constname = "global";

$a = 'classname';
$obj = new $a;  // print classname::__construct
$b = 'funcname';
$b();   // print funcname
echo constant('constname'), "\n";   // prints global

?>
