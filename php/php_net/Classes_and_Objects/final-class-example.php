<?php
final class BaseClass
{
    public function test()
    {
        echo "BaseClass::test() called\n";
    }

    final public function moreTesting()
    {
        echo "BaseClass::moreTesting() called\n";
    }
}

class ChildClass extends BaseClass {}
?>
