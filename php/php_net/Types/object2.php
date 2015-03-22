<?php
$obj = (object) array('1' => 'foo');
var_dump(isset($obj->{'1'}));  // output 'bool(false)'
var_dump(key($obj));  // outputs 'int(1)'
?>
