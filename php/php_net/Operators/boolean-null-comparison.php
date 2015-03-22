<?php
// Bool and null are compared as bool always
var_dump(1 == TRUE);
var_dump(0 == FALSE);
var_dump(100 < TRUE);
var_dump(-10 < FALSE);
var_dump(min(-100, -10, NULL, 10, 100));
?>
