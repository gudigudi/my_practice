<?php
/*
 * 1. select and connect to the database
 * 2. access SQL query
 * 3. print query result in HTML formatation
 */


# 1.select and connect to the database
$mysql_host = $_POST["host"];
$mysql_user = $_POST["user"];
$mysql_password = $_POST["password"];
$mysql_db = $_POST["database"];
$mysql_table = $_POST["table"];

$conn = mysql_connect($mysql_host, $mysql_user, $mysql_password)
    or die("Could not connect: " . mysql_error());
echo "Connected successfully";
mysql_select_db($mysql_db) or die("Could not select database");


# 2.access SQL query
$query = "SELECT * FROM $mysql_table";
$result = mysql_query($query) or die("Query failed: " . mysql_error());


# 3.print query result in HTML formatation
echo "<table>\n";
while ($line = mysql_fetch_array($result, MYSQL_ASSOC)) {
    echo "\t<tr>\n";
    foreach ($line as $col_value) {
        echo "\t\t<td>$col_value</td>\n";
    }
    echo "\t</tr>\t";
}
echo "</table>";

?>
