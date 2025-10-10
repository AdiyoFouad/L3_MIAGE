<?php 

    require_once '../config/config.php';
    require_once '../DAO/ContactDAO.php';

class ContactModel {
    private int $id;
    private string $nom;
    private string $prenom;
    private string $email;
    private string $telephone;
    public function __construct($id, $nNom, $nPrenom, $nEmail, $nTelephone) {
        $this->id = $id;
        $this->nom = $nNom;
        $this->prenom = $nPrenom;
        $this->email = $nEmail;
        $this->telephone = $nTelephone;
    }
    
    public static function getContact(int $id): ContactModel {
        global $pdo;
        $contactDAO = new ContactDAO($pdo);
        return $contactDAO->getById($id);
    } 

    public function add() {
        global $pdo;
        $contactDAO = new ContactDAO($pdo);
        $contactDAO->addContact($this);
    }
    public function update() {
        global $pdo;
        $contactDAO = new ContactDAO($pdo);
        $contactDAO->editContact($this);
    }
    public function delete() {
        global $pdo;
        $contactDAO = new ContactDAO($pdo);
        $contactDAO->deleteContact($this->id);
    }

    public function getId(): string {
        return $this->id;
    }

    public function getNom():string {
        return $this->nom;
    }
    public function getPrenom():string {
        return $this->prenom;
    }
    public function getEmail():string {
        return $this->email;
    }
    public function getTelephone():string {
        return $this->telephone;
    }

    


}

?>