<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des contacts</title>
    <link rel="stylesheet" href="public/css/styles.css"> 
</head>
<body>
    <h1>Liste des contacts</h1>
<a href="index.php?action=add" class="btn btn-add">Ajouter un contact</a>

<table>
    <thead>
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <?php if (!empty($contacts)): ?>
            <?php foreach ($contacts as $contact): ?>
                <tr>
                    <td><?= htmlspecialchars($contact->getNom()) ?></td>
                    <td><?= htmlspecialchars($contact->getPrenom()) ?></td>
                    <td><?= htmlspecialchars($contact->getEmail()) ?></td>
                    <td><?= htmlspecialchars($contact->getTelephone()) ?></td>
                    <td>
                        <a href="index.php?action=view&id=<?= $contact->getId() ?>" class="btn btn-view">Voir</a>
                        <a href="index.php?action=edit&id=<?= $contact->getId() ?>" class="btn btn-edit">Modifier</a>
                        <a href="index.php?action=delete&id=<?= $contact->getId() ?>" class="btn btn-delete" onclick="return confirm('Supprimer ce contact ?');">Supprimer</a>
                    </td>
                </tr>
            <?php endforeach; ?>
        <?php else: ?>
            <tr>
                <td colspan="5">Aucun contact trouvé.</td>
            </tr>
        <?php endif; ?>
    </tbody>
</table>

</body>
</html>
