<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier un contact</title>
    <link rel="stylesheet" href="public/css/styles.css">
</head>
<body>

    <h1>Modifier un contact</h1>

    <?php if (isset($contact)) : ?>
        <form action="index.php?action=edit" method="POST" class="contact-form">

            <input type="text" name="id" value="<?= htmlspecialchars($contact->getId()) ?>" hidden >

            <label for="nom">Nom :</label>
            <input type="text" name="nom" id="nom" value="<?= htmlspecialchars($contact->getNom()) ?>" required>

            <label for="prenom">Prénom :</label>
            <input type="text" name="prenom" id="prenom" value="<?= htmlspecialchars($contact->getPrenom()) ?>" required>

            <label for="email">Email :</label>
            <input type="email" name="email" id="email" value="<?= htmlspecialchars($contact->getEmail()) ?>" required>

            <label for="telephone">Téléphone :</label>
            <input type="text" name="telephone" id="telephone" value="<?= htmlspecialchars($contact->getTelephone()) ?>" required>

            <button type="submit" class="btn btn-edit">Enregistrer les modifications</button>
            <a href="index.php?action=home" class="btn">Annuler</a>
        </form>
    <?php else : ?>
        <p>Contact introuvable.</p>
        <a href="index.php?action=home" class="btn">Retour à la liste</a>
    <?php endif; ?>

</body>
</html>
