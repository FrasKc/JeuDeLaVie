# Game of Life

Le "Game of Life" est une implémentation du célèbre automate cellulaire conçu par le mathématicien britannique John Horton Conway en 1970. Ce projet fournit une interface visuelle et interactive pour explorer les comportements complexes qui émergent de règles simples.

## Installation

Pour exécuter ce projet, vous aurez besoin de :

- Java (version 11 ou supérieure)
- Maven (pour la gestion des dépendances et la construction du projet)
- MongoDB (pour le stockage des données, comme les règles du jeu)

### Étapes d'installation

1. **Cloner le dépôt :**

```bash
git clone https://github.com/FrasKc/JeuDeLaVie.git
```

2. **Configurer MongoDB :**

Assurez-vous que MongoDB est installé et en cours d'exécution sur votre machine. Configurez l'URI de connexion dans _src/main/resources/application.properties_.

3. **Construire le projet :**

Dans le répertoire du projet, exécutez :

```bash
mvn clean install
```

4. **Exécuter le projet :**

Après la construction du projet, exécutez le fichier JAR généré :

```bash
java -jar target/JeuDeLaVie-1.0.0.jar
```

l'application sera accessible à l'adresse suivante : [http://localhost:8081/login](http://localhost:8081/login)

## Utilisation

### Démarrer une nouvelle partie
- Accédez à la page de connexion (http://localhost:8081/login) ou sur la page d'inscription si vous n'avez pas de compte (http://localhost:8081/register).
- Connectez-vous avec un nom d'utilisateur et un mot de passe valides.
- Vous serez redirigé vers la page principale du jeu (http://localhost:8081).
- Cliquez sur "Start Game" pour initialiser le tableau avec une configuration aléatoire.
- Le jeu commence à évoluer suivant les règles prédéfinies.


### Interagir avec le jeu
- **Chat en direct** : Utilisez le chat pour discuter avec d'autres utilisateurs connectés ou pour proposer de nouvelles règles de jeu.
- **Modifier les règles** : Envoyez un message dans le format : **rule:2,3** pour proposer une nouvelle règle. Une proposition doit être votée par les utilisateurs avant d'être appliquée.
- **Arrêter/Reprendre le jeu** : Utilisez le bouton "Stop Game" pour arrêter le jeu. Relancez-le avec "Start Game".

### Visualiser les règles actuelles
La règle en cours est affichée sous la forme "Reste en vie avec X voisins, devient vivante avec Y voisins". Elle est mise à jour en temps réel selon les propositions acceptées par la communauté.