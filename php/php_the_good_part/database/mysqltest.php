<?php
/**
 * Created by PhpStorm.
 * User: gudi
 * Date: 12/17/14
 * Time: 5:04 PM
 */

$mydb = new mysqli('localhost', 'root', 'ZBQ%@!dfsq', 'book');
$sql = "select * from library";

$result = $mydb->query($sql);

while ( $row = $result->fetch_assoc() ) {
    echo $row['fname'] . $row['lname'];
    echo "<br />";
}

$result->close();
$mydb->close();
?>