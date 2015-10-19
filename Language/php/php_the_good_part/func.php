<?php
function show_stuff() {
    $secondName = "Beck";
    echo "inside show_stuff: " . $firstname . " " . $secondName;
}

$firstname = "Peter";       // variable has file-wide scope
                            // (excluding functions)
echo $firstname . "<br />"; // Peter is displayed
show_stuff();               // only Beck is displayed because $firstname is
                            // not within the scope of the function,
echo "Outside function " . $secondName;

define("SYS_OWNER", "Peter");
define("SYS_MGR", "SImon", true);
echo "System owner is:" . SYS_OWNER . "<br />";
define("SYS_OWNER", "Michael");
echo "System owner is:" . SYS_OWNER . "<br />";
echo "System manager is:" . SYS_MGR . "<br />";
echo "System manager is:" . SYS_mgr . "<br />";
?>
