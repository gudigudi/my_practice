<?php
function foo(&$foo) { }

foo($a);  // $a is "created" and assigned to null

$b = array();
foo($b['b']);
var_dump(array_keys_exist('b', $b));

$c = new StdClass;
foo($c->d);
var_dump(property_exists($c, 'd'));

?>
