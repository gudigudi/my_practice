<?php

print strtotime("22nd December 1979") . "\n";
print strtotime("22 Dec. 1979 17:30") . "\n";
print strtotime("1979/12/22") . "\n";

$mydate = strtotime("Christmas 1979");
if ($mydate == -1) {
    print "Date conversion failed!";
} else {
    print "Date conversion succeeded!" . "\n";
}

print strtotime("Next Sunday") . "\n";
print strtotime("2 days", time() - (86400 * 2)) . "\n";
print strtotime("1 year ago", 123456789) . "\n";

?>
