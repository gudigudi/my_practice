<?php
echo __LINE__."\n";
echo __FILE__."\n";
echo __DIR__."\n";

function func()
{
    echo __FUNCTION__."\n";
}

class cl
{
    //echo __CLASS__."\n";
}

echo __TRAIT__;
echo __METHOD__;
echo __NAMESPACE__;
?>
