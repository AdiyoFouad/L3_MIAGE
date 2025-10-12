<?php 

require_once "./models/ContactModel.php";
require_once "./config/config.php";

class ContactDAO {
    private PDO $pdo;
    public function __construct(PDO $npdo) {
        $this->pdo = $npdo;
    }

    public function getContacts() : array{
        $stmt = $this->pdo->prepare("SELECT * FROM contacts");
        $contacts = [];
        while ($row = $stmt->fetch()) {
            $contacts[] = new ContactModel($row['id'], $row['nom'], $row['prenom'], $row['email'], $row['telephone']);
        }
        return $contacts;
    }

    public function getById(int $id) : ContactModel {
        $stmt = $this->pdo->prepare("SELECT * FROM contacts WHERE id = ?");
        $stmt->execute(array($id));
        $row = $stmt->fetch();
        return new ContactModel($row['id'], $row['nom'], $row['prenom'], $row['email'], $row['telephone']);
    }

    public function addContact(ContactModel $contact) : void{
        $stmt = $this->pdo->prepare("INSERT INTO contacts VALUES (?, ?, ?, ?, ?)");
        $stmt->execute(array("", $contact->getNom(), $contact->getPrenom(), $contact->getEmail(), $contact->getTelephone()));
    }
    public function editContact(ContactModel $contact) : void{
        $stmt = $this->pdo->prepare("UPDATE contacts SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id = ?");
        $stmt->execute(array($contact->getNom(), $contact->getPrenom(), $contact->getEmail(), $contact->getTelephone(), $contact->getId()));
    }

    public function deleteContact(int $contactId) : void{
        $stmt = $this->pdo->prepare("DELETE FROM contacts WHERE id = ?");
        $stmt->execute(array($contactId));
    }
}

?>