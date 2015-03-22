<?php
// define a simple range generator
function generatoRange($start, $end, $step = 1) {
    for ($i = $start; $i < $end; $i += $step) {
        // yield one result at a time
        yield $i;
    }
}

foreach (generatoRange(0, 1000000) as $number){
    echo $number . "\n";
}

?>
