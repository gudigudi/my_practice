<?php
# connect.php: connect to the MySQL server

try {
    $dsn = "mysql:host=localhost;dbname=cookbook";
    $dbh = new PDO($dsn, "user", "password");
    print("Connected\n");
} catch (PDOException $e) {
    die("Cannot connect to server\n");
}
$dbh = NULL;
print("Disconnected\n");
?>
