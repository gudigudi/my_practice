<?php
$login = $_GET["login"];
$logname = $_GET["logname"];
if ($login == 1) {
    echo "Welcome ". $logname;
} else {
    echo "login failed... please try again " . $logname;
}
?>
