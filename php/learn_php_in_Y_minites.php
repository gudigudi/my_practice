<?php

// print and echo
print('Hello ');
echo "World\n";

// string " and '
$sgl_quotes = '$string';
echo $sgl_quotes . "\n";
$dbl_quotes = "This is a $sgl_quotes.";
echo $dbl_quotes . "\n";

// array
$associative = ['One' => 1, 'Two' => 2, 'Three' => 3];
echo $associative['One'] . "\n";
$array = ['One', 'Two', 'Three'];
echo $array[0] . "\n";

for ($i = 0; $i < 10; $i++) {
    echo $i;
}
echo "\n";

$wheels = ['bicycle' => 2, 'car' => 4];
foreach ($wheels as $wheel_count) {
    echo $wheel_count . "\n";
}
echo "\n";

class MyClass
{
    const MY_CONST = 'value';
    static $staticVar = 'static';

    public $property = 'public';
    public $instanceProp;
    protected $prot = 'protected';
    private $priv = 'private';

    public function __construct($instanceProp)
    {
        $this->instanceProp = $instanceProp;
    }

    public function myMethod()
    {
        print 'MyClass';
    }
    final function myCannotOverrideMe()
    {
    }
    public static function myStaticMethod()
    {
        print 'I am static';
    }
}

echo MyClass::MY_CONST . "\n";
echo MyClass::$staticVar . "\n";
MyClass::myStaticMethod() . "\n";
?>
