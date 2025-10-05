<?php
    require_once("model.php");
    require_once("../classes/Compte.php");

    if (isset($_POST["login"]) && isset($_POST["password"])) {
        $compte = new Compte($_POST["login"], $_POST["password"]);
        if (existe($pdo, $compte->getLogin(), $compte->getPassword())) {
            header("Location: home.php");
        }else {
            header("Location: login.php?err=Identifiants invalides");
        }
    }

?>
