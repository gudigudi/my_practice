<?php
class obj implements arrayaccess
{
    public function offsetSet($offset, $value)
    {
        var_dump(__METHOD__);
    }
    public function offsetExists($var)
    {
        var_dump(__METHOD__);
        if ($var == "foobar") {
            return true;
        }
        return false;
    }
    public function offsetUnset($var)
    {
        var_dump(__METHOD__);
    }
    public function offsetGet($var)
    {
        var_dump(__METHOD__);
        return "value";
    }
}
?>
