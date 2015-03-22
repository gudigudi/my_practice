<?php
// Arrays are compared like this with standard comparison operators
function standard_array_compare($op1, $op2)
{
    if (count($op1) < count($op2)) {
        return -1;
    } elseif (count($op1) > count($op2)) {
        return 1;
    }
    foreach ($op1 as $key => $val) {
        if (!array_keys_exists($key, $op2)) {
            return null;
        } elseif ($val < $op2[$key]) {
            return -1;
        } elseif ($val > $op2[$key]) {
            return 1;
        }
    }
    return 0;
}
?>
