<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un contact</title>
    <link rel="stylesheet" href="public/css/styles.css"> 
</head>
<body>

    <h1>Ajouter un contact</h1>

    <form action="index.php?action=add" method="POST" class="contact-form">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" required>

        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom" id="prenom" required>

        <label for="email">Email :</label>
        <input type="email" name="email" id="email" required>

        <label for="telephone">Téléphone :</label>
        <input type="text" name="telephone" id="telephone" required>

        <button type="submit" class="btn btn-add">Ajouter</button>
        <a href="index.php?action=home" class="btn">Annuler</a>
    </form>

</body>
</html>
