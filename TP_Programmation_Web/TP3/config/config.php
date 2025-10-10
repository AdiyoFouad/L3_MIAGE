<?php

$host = "localhost";
$username="root";
$password= "";
$dbname = "contacts";
$charset = 'utf8';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=$charset", $username, $password);
} catch (Exception $e) {
    die("Erreur lors de l'ouverture de la base de donnée: " . $e->getMessage());
}
?>