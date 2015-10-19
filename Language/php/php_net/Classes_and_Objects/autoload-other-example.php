<?php

function __autoload($name)
{
    var_dump($name);
}

class Foo implements ITest {}

/*
string(5) "ITest"

Fatal error: Interface 'ITest' not found in ...
*/

?>
