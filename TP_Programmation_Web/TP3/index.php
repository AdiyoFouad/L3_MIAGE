
<?php


require_once 'controllers/HomeController.php';
require_once 'controllers/AddContactController.php';
require_once 'controllers/ViewContactController.php';
require_once 'controllers/EditContactController.php';
require_once 'controllers/DeleteContactController.php';

// Vérifier quelle action demander (par exemple : "add", "view", "edit", etc.)
$action = isset($_GET['action']) ? $_GET['action'] : 'home'; // action par défaut = 'home'

switch ($action) {
    case 'home':
        $controller = new HomeController();
        $controller->index();
        break;

    case 'add':
        $controller = new AddContactController();
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $controller->addContact();
        } else {
            $controller->showForm();
        }
        break;

    case 'view':
        $controller = new ViewContactController();
        $controller->viewContact();
        break;

    case 'edit':
        $controller = new EditContactController();
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $controller->editContact(); 
        } else {
            $controller->showForm(); 
        }
        break;

    case 'delete':
        $controller = new DeleteContactController();
        $controller->deleteContact(); 

    default:
        echo "Action inconnue!";
}

?>

