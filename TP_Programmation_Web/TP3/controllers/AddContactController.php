<?php

    require_once '../models/ContactModel.php';

    class AddContactController {
        public function addContact() { 
            if (isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["email"]) && isset($_POST["telephone"])) {
                $contact = new ContactModel(-1, $_POST["nom"], $_POST["prenom"], $_POST["email"], $_POST["telephone"]);
                $contact->add();
                header("Location: index.php");
            }
        }

        public function showForm() {
            // Affichage du formulaire
            require '../views/add_contact.php';
        }
    }
?>