<?php
function gen_three_nulls()
{
    foreach (range(1, 2) as $i) {
        yield;
    }
}

var_dump(iterator_to_array(gen_three_nulls()));
?>
