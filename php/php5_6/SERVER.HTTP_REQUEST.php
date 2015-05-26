<?php

if (isset($_SERVER['HTTP_REFERER'])) {
    print "The page you were on previously was {$_SERVER['HTTP_REFERER']}<br />";
} else {
    print "You didn't click any links to get here<br />";
}
?>
