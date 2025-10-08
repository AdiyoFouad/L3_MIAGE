<?php 

class ContactModel {

    //mettre le contactDAO comme un objet static de la classe et l'utilisé à chaque fois. Du coup pas besoin de le passer en parramètre
    
    private static $contactDAO = new ContactDAO();
    
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
        return ContactModel::$contactDAO->getById($id);
    } 

    public function add() {
        ContactModel::$contactDAO->addContact($this);
    }
    public function update(ContactDAO $contactDAO) {
        ContactModel::$contactDAO->editContact($this);
    }
    public function delete(ContactDAO $contactDAO) {
        ContactModel::$contactDAO->deleteContact($this->id);
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