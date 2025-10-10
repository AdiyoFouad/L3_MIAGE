<?php

    require_once '../config/config.php';
    require_once("../DAO/ContactDAO.php");

    class HomeController {
        public function index() {
            global $pdo;
            $contactDAO = new ContactDAO($pdo);
            $contacts = $contactDAO->getContacts();
            require '../views/home.php';
        }
    }
?>

?>