<?php
// An example class
class MyClass
{
    /**
     * A test function
     *
     * First parameter must be an object of type OtherClass
     */
    public function test(OtherClass $otherclass)
    {
        echo $otherclass->var;
    }

    /**
     * Another test function
     *
     * First parameter must be an array
     */
    public function test_array(array $input_array)
    {
        print_r($input_array);
    }

    /**
     * First parameter must be iterator
     */
    public function test_interface(Traversable $iterator)
    {
        echo get_class($iterator);
    }

    /**
     * Firsst parameter must be callable
     */
    public function test_callable(callable $callback, $data)
    {
        call_user_func($callback, $data);
    }
}

// Another example class
class OtherClass
{
    public $var = 'Hello World';
}
?>
