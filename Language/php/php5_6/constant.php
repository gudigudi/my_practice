<?php

define("MINSIZE", 50);
echo MINSIZE;
echo constant("MINSIZE");


// Micro
echo __LINE__;
echo __FILE__;
function echoFunctionName() {
  echo __FUNCTION__;
}
echoFunctionName();

?>
