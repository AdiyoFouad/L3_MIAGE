<?php
 class Compte implements JsonSerializable{
    private string $login;
    private string $password;
    private static int $counter = 0;

    public function __construct($l, $p) {
        $this->login = $l;
        //$this->password = $p;
        $this->password = password_hash($p, PASSWORD_DEFAULT);
        self::$counter++;
    }

    public function getLogin(): string {
        return $this->login;
    }

    public function getPassword(): string {
        return $this->password;
    }

    public function setLogin($l){
        $this->login = $l;
    }

    public function setPassword($p){
        $this->password = $p;
    }

    public function masquerLogin(): string {
        $subLogin = substr($this->login,0, 2) . "***" . substr($this->login,-1);
        return $subLogin;
    }

    public function toArray(): array{
        $compteArray = array(
            "login" => $this->login,
            "password" => $this->password
        );
        return $compteArray;
    }

    public function checkPassword($plain): bool{
        return password_verify($plain, $this->password) ;
    }

    public function changePassword($new){
        if (self::isStrongPassword($new)) {
            $this->password = password_hash($new, PASSWORD_DEFAULT);
        } else {
            throw new Exception("Mot de passe trop Faible");
        }
    }

    public function  __toString(): string{
        return "\nLe login est : " . $this->login . " et le mot de passe est : " . $this->password; 
    }

    public static function isValidLogin(string $login): bool {
        return (bool) preg_match('/^[a-zA-Z0-9._-]{3,20}$/', $login);
    }

    public static function isStrongPassword(string $pwd): bool{
        //return preg_match('/^[A-Za-z0-9]{8,20}$/', $pwd);
        return (bool) preg_match('/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/', $pwd);
    }

    public static function fromArray(array $row): Compte{
       return new Compte($row["login"], $row["password"]);
    } 

    
    public static function count(): int{
        return self::$counter;
    } 

    public static function resetCounter(): void{
        self::$counter = 0;
    }

    public static function cmpByLoginLen(Compte $a, Compte $b): int{
        /* Le comparateur de usort(tab, fncCallback(a,b))
        * Renvoie un entier négatif si le premier élément est plus petit que le second.
        * Renvoie un entier positif si le premier élément est plus grand que le second.
        * Renvoie 0 si les deux sont égaux.
        */
        return strlen($a->login)  -  strlen($b->login);
    }

    public function jsonSerialize(): array{
        return [
            'login' => $this->login, 
        ];
    }

    public static function main(){
        //Main pour tester la classe
        
        $compt1 = new Compte("per1du77","JeanNaymar.+");
        $compt2 = new Compte("jujuDu35","Elle.EN.Neymar2");

        echo "<b>Test masquerLogin() et toArray() instance 1 : </b>" . $compt1->masquerLogin();
        //foreach($compt1->toArray() as $cle => $val) { print($cle." => ".$val."\n"); };
        echo '<pre>';
        print_r($compt1->toArray());
        echo '</pre>';

        echo "<b>Test masquerLogin() et toArray() instance 2 : </b>" . $compt2->masquerLogin();
        //foreach($compt2->toArray() as $cle => $val) { print($cle." => ".$val."\n"); };
        echo '<pre>';
        print_r($compt2->toArray());
        echo '</pre>';

        /*----------------------------------------------------------------------------------*/

        echo "<b>Test créer des comptes à partir d'un tableau source</b>";
        $tab = [
            [ "login" => "alice",    "password" => "12345" ],       // Mot de passe trop simple
            [ "login" => "bob",      "password" => "" ],            // Mot de passe manquant
            [ "login" => "",         "password" => "secret123" ],   // Login manquant
            [ "login" => "charlie",  "password" => "superSafe!42" ],// Correct
            [ "login" => "david",    "password" => "123" ],         // Mot de passe trop court
            [ "login" => "eve",      "password" => "password" ],    // Mot de passe trop commun
            [ "login" => "frank",    "password" => "myp@ssw0rd" ],  // Correct
            [ "login" => "george",   "password" => null ],          // Mot de passe nul
            [ "login" => "ab",    "password" => "abcdefghij" ],     // Login trop court
        ];

        $tabDeCompte = array();

        foreach ($tab as $value) {
            $tabDeCompte[] = Compte::fromArray($value);
        }
        echo '<pre>';
        print_r($tabDeCompte);
        echo '</pre>';
        
        /*----------------------------------------------------------------------------------*/

        echo "<b>Test pour filtrer les logins invalides</b>";

        $tabDeCompteLoginFiltre = array();
        
        foreach ($tabDeCompte as $value) {
            if (Compte::isValidLogin( $value->getLogin() )) {
                $tabDeCompteLoginFiltre[] = $value;
            }
        }
        echo '<pre>';
        print_r($tabDeCompteLoginFiltre);
        echo '</pre>';

        
        /*----------------------------------------------------------------------------------*/

        echo "<b>Test pour tester les mots de passe</b>";
        $testsPasswords = [
            "12345" => Compte::isStrongPassword("12345"), //mot de passe trop court
            "" => Compte::isStrongPassword(""), // vide
            "secret123" => Compte::isStrongPassword("secret123"), // pas de maj
            "superSafe!42" => Compte::isStrongPassword("superSafe!42"), //correct
            "myp@ssw0rd " => Compte::isStrongPassword("myp@ssw0rd"), // espace non autorisé
            "P@ssw0rd123" => Compte::isStrongPassword("P@ssw0rd123"), // correct
        ];
        echo '<pre>';
        print_r($testsPasswords);
        echo '</pre>';

        
        /*----------------------------------------------------------------------------------*/

        echo "<b>Test pour trier les comptes</b>";
        usort($tabDeCompte, function($a, $b) {
            return Compte::cmpByLoginLen($a, $b);
        });
        echo '<pre>';
        print_r($tabDeCompte);
        echo '</pre>';

        
        
        /*----------------------------------------------------------------------------------*/

        echo "<b>Test pour afficher JSON des comptes</b>";
        foreach ($tabDeCompte as $key => $value) {
            echo '<pre>';
            print_r($value->jsonSerialize());
            echo '</pre>';
        }
    }

 }

 

?>
