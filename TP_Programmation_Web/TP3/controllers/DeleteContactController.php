<?php

    require_once '../models/ContactModel.php';
    require_once '../config/config.php';
    require_once '../DAO/ContactDAO.php';
    class EditContactController {
        public function deleteContact() {
            if (isset($_POST['id'])) {
                global $pdo;
                $contactDAO = new ContactDAO($pdo);
                $contactDAO->deleteContact($_POST['id']);
                header("Location: index.php");
            }
        }

        public function showConfirmation() {
            if (isset($_GET['id'])) {
                $contactId = $_GET["id"];
                require '../views/edit_contact.php';
            }
        }

    }
    

?>