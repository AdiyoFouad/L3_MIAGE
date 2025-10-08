<?php

if (isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["email"]) && isset($_POST["telephone"])) {
    $contact = new ContactModel(-1, $_POST["nom"], $_POST["prenom"], $_POST["email"], $_POST["telephone"]);
    $contact->add();
    header("Location: HomeController.php");
} else {
    
    header("Location: ../views/view_contact.php");
}
?>