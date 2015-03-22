<?php
class SimpleClass
{
    // invalid property declarations:
    public $var1 = 'hello ' . 'world';
    public $var2 = <<<EOD
hello world
EOD;
    public $var3 = 1 + 2;
    public $var4 = self::myStaticMethod();
    public $var5 = $myVar;

    // valid property declarations:
    public $var6 = myConstant;
    public $var7 = array(true, false);

    // This is allowed only in PHP 5.3.0 and later.
    public $var8 = <<<'EOD'
hello world
EOD;
}
?>
