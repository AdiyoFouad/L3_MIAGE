<?php
$host = "localhost";
$dbname = "mytp1bd";
$username = 'root';
$charset = 'utf8';
$password = '';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname;charset=$charset", $username, $password);
} catch (Exception $e) {
    die("Erreur lors de l'ouverture de la base de donnée: " . $e->getMessage());
}

?>
