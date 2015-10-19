<?php
namespace {

    function sayHello($name) {
        echo "greetings from the global scope: " . $name;
    }

include "some_samples.php";

Sample1\sayHello("Frank");
echo "\n";
Sample2\sayHello("Peter");
echo "\n";

use Sample1 as S1;
S1\sayHello("Frank");
echo "\n";
Sample2\sayHello("Peter");
echo "\n";

sayHello("Charlie");
echo "\n";

}
?>
