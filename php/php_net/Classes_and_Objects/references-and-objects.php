<?php
class A
{
    public $foo = 1;
}

$a = new A;
$b = $a;

$b->foo = 2;
echo $a->foo . "\n";

$c = new A;
$d = &$c;

$d->foo = 2;
echo $c->foo . "\n";

$e = new A;

function foo($obj)
{
    $obj->foo = 2;
}

foo($e);
echo $e->foo . "\n";

?>
