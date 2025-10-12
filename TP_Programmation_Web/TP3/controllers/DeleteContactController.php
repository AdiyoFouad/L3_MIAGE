<?php

    require_once ("./models/ContactModel.php");
    require_once ("./config/config.php");
    require_once ("./DAO/ContactDAO.php");
    class DeleteContactController {
        public function deleteContact() {
            if (isset($_GET['id'])) {
                global $pdo;
                $contactDAO = new ContactDAO($pdo);
                $contactDAO->deleteContact($_GET['id']);
                header("Location: index.php");
            }
        }


    }
    

?>