<?php
/**
 * Define MyClass
 */
class MyClass
{
    // Declare a public constructor
    public function __construct() {}

    // Declare a public method
    public function MyPublic() {}

    // Declare a protected method
    protected function MyProtected() {}

    // Declare a private method
    protected function MyPrivate() {}

    // This is public
    function Foo()
    {
        $this->MyPublic();
        $this->MyProtected();
        $this->MyPrivate();
    }
}

$myclass = new MyClass;
$MyClass->MyPublic();
$myclass->MyProtected();
$myclass->MyPrivate();
$myclass->Foo();


/**
 * Define MyClass2
 */
class MyClass2 extends MyClass
{
    // This is public
    function Foo2()
    {
        $this->MyPublic();
        $this->MyProtected();
        $this->MyPrivate();
    }
}

$myclass2 = new MyClass2;
$myclass2->MyPublic();
$myclass2->Foo2();

class Bar
{
    public function test()
    {
        $this->testPrivate();
        $this->testPublic();
    }

    public function testPublic()
    {
        echo "Bar::testPublic\n";
    }

    private function testPrivate()
    {
        echo "Bar::testPrivate\n";
    }
}

class Foo extends Bar
{
    public function testPublic()
    {
        echo "Foo::testPublic\n";
    }

    private function testPrivate()
    {
        echo "Foo::testPrivate\n";
    }
}

$myFoo = new foo();
$myFoo->test();

?>
