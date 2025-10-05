<?php

require_once("classes/Compte.php");

if (isset($_GET['login']) && isset($_GET['password'])) {
    $user = new Compte( $_GET['login'], $_GET['password']);
    echo "Le login masqué est : " . $user->masquerLogin();
}
?>
