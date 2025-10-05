<?php

 require_once("../db.php");

 function existe(PDO $pdo, string $login, string $password): bool{
    $stmt = $pdo->prepare("SELECT * FROM users WHERE login = ? AND password = ?");
    $stmt->execute(array($login, $password));
    return $stmt->rowCount() > 0;
 }

?>
