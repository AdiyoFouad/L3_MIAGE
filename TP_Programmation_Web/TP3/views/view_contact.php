<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails du contact</title>
    <link rel="stylesheet" href="public/css/styles.css">
</head>
<body>

    <h1>Détails du contact</h1>

    <?php if (isset($contact)) : ?>
        <div class="contact-details">
            <p><strong>Nom :</strong> <?= htmlspecialchars($contact->getNom()) ?></p>
            <p><strong>Prénom :</strong> <?= htmlspecialchars($contact->getPrenom()) ?></p>
            <p><strong>Email :</strong> <?= htmlspecialchars($contact->getEmail()) ?></p>
            <p><strong>Téléphone :</strong> <?= htmlspecialchars($contact->getTelephone()) ?></p>
        </div>

        <div class="actions">
            <a href="index.php?action=edit&id=<?= $contact->getId() ?>" class="btn btn-edit">Modifier</a>
            <a href="index.php?action=delete&id=<?= $contact->getId() ?>" class="btn btn-delete" onclick="return confirm('Supprimer ce contact ?');">Supprimer</a>
            <a href="index.php?action=home" class="btn">Retour à la liste</a>
        </div>
    <?php else : ?>
        <p>Contact introuvable.</p>
        <a href="index.php?action=home" class="btn">Retour à la liste</a>
    <?php endif; ?>

</body>
</html>
