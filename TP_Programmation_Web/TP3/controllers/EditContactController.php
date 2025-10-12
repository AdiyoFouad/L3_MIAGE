<?php

    require_once ("./models/ContactModel.php");
    require_once ("./config/config.php");
    require_once ("./DAO/ContactDAO.php");
    class EditContactController {
        public function editContact() {
            if (isset($_POST['id']) && isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["email"]) && isset($_POST["telephone"])) {
                $contact = new ContactModel($_POST['id'], $_POST["nom"], $_POST["prenom"], $_POST["email"], $_POST["telephone"]);
                $contact->update();
                header("Location: index.php?action=view&id=" . $contact->getId());
            }
        }

        public function showForm() {
            // Affichage du formulaire
            global $pdo;
            $contactDAO = new ContactDAO($pdo);
            $contact = $contactDAO->getById($_GET['id']);
            require './views/edit_contact.php';
        }
    }
    

?>