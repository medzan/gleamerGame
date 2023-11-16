## Introduction

Le code fourni implémente un jeu de société où les joueurs progressent sur un plateau en répondant à des questions de différentes catégories.

Chaque fois qu'un joueur lance les dés, il se déplace d'un certain nombre de cases. La case sur laquelle il atterrit détermine la catégorie de question à laquelle il doit répondre.

Si la réponse est correcte, le joueur gagne un point. S'il se trompe, il est envoyé dans la case de pénalité. Le joueur ne peut pas rejouer tant qu'il n'est pas sorti de la case de pénalité.

Pour sortir de la case de pénalité temporairement, le joueur doit lancer un dé et obtenir un nombre impair. S'il obtient un nombre pair, il reste dans la case de pénalité.
Une fois qu'un joueur est sorti de la case de pénalité, il peut rejouer normalement.
Le premier joueur à collecter six points gagne la partie.

## Problèmes de conception et de mise en œuvre

Le code fourni présente plusieurs problèmes de conception et de mise en œuvre :

- **Couplage serré :** Le code est fortement couplé à une seule classe avec beaucoup de responsabilités et des
  dépendances directes entre les méthodes. Cela rend difficile la modification, l'évolution ou la réutilisation de
  composants individuels sans affecter l'ensemble du l'application.
- **Manque d'abstraction :** Le code manipule directement des tableaux et des listes chaînées, ce qui obscurcit la
  logique et les structures de données sous-jacentes.
- **Logique dispersée :** La logique du jeu est dispersée sur plusieurs méthodes, ce qui rend difficile de suivre le
  flux du jeu et de comprendre le processus décisionnel global.
- **Manque de configuration :** L'évolution du code est difficile car il n'y a pas de notion de configuration ou de
  couche fonctionnelle.

## Améliorations implémentées

Pour résoudre ces problèmes, les améliorations suivantes ont été implémentées :

- **Encapsulation de la logique du jeu :** La logique du jeu a été encapsulée dans des classes ou services distincts,
  chacun responsable d'un aspect spécifique du jeu, tel que la gestion des joueurs, la gestion des questions et la
  progression du jeu.

**Liste des composants implémentés et leurs responsabilités**

- **IPlayerService** :
    - Maintenir l'état des joueurs et de leurs positions sur le plateau de jeu.
    - Gérer les transitions entre les tours de jeu.
    - Gérer l'octroi et le retrait des autorisations de sortie temporaire de la case de pénalité.
    - Assurer le bon fonctionnement des fonctionnalités liées aux joueurs, telles que l'ajout, le déplacement et
      l'attribution de pièces d'or.
- **IQuestionService:**
    - Gérer l'initialisation et la préparation du pool de questions.
    - Sélectionner une question appropriée en fonction de la catégorie et de la position du joueur actuel.
    - Fournir des informations sur la catégorie de la question à poser au joueur actuel.
- **IQuestionGenerator**
    - Générer un nombre spécifié de questions en fonction d'une catégorie donnée.
    - Créer une file d'attente de questions à l'aide de la structure de données `ArrayDeque` pour des opérations FIFO
      efficaces.
- **GameBoardManager**
    - Fournir la position de départ du joueur
    - Calculer la position suivante du joueur
- **IGameService**
    - Gérer les règles liées à la sortie temporaire de la case de pénalité.
    - Évaluer les conditions de victoire pour le joueur actuel.
    - Assurer le respect des conditions préalables au démarrage du jeu.
    - Lever une exception si les conditions d'initialisation ne sont pas remplies.

## Tests de non regression

La classe **RegressionPreventionTest** permet d'exécuter via la
méthode `assertCurrentImplementationOutput_sameAsLegacyOutput` le code legacy `com.ezangui.gleamer.legacy.LegacyGame` (
inchangé) et le nouveau code sur la même liste de joueurs avec la même configuration, puis teste le résultat (affichage
des println).