<?php

    require_once ("./models/ContactModel.php");
    require_once ("./config/config.php");
    require_once ("./DAO/ContactDAO.php");
    class ViewContactController {
        public function viewContact() {
            if (isset($_GET['id'])) {
                global $pdo;
                $contactDAO = new ContactDAO($pdo);
                $contact = $contactDAO->getById($_GET['id']);
                require './views/view_contact.php';
            }
        }

    }
    

?>