<?php
try {
    $dsn = "mysql:host=localhost;dbname=cookbook";
    $dbh = new PDO($dsn, "baduser", "badpass");
    $dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    print("Connected\n");
} catch (PDOException $e) {
    print("Cannot connect to server\n");
    print("Error code: " . $e->getCode() . "\n");
    print("Error message: " . $e->getMessage() . "\n");
}