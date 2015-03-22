<?php
function __autoload($name)
{
    echo "What to load $name.\n";
    throw new Exception("Unable to load $name.");
}

try {
    $obj = new NonLoadableClass();
} catch (Exception $e) {
    echo $e->getMessage(), "\n";
}
?>
