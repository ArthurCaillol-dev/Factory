# Factory

Bienvenue dans Factory, un jeu de tower defense 2D créé en Java.

## Instructions d'exécution

0. **Assurez-vous d'être sur une machine Linux**
    En effet, le projet à été pensé et créer sur un système linux. Aucune implémentation n'a été faite pour qu'il fonctionne sur un système Windows

1. **Assurez-vous d'avoir Java installé sur votre machine.**

2. **Téléchargez le code source du jeu depuis le dépôt Git :**
    ```
    git@github.com:ArthurCaillol-dev/Factory.git
    ```

3. **Accédez au répertoire du projet :**
    ```
    cd factory
    ```

4. **Compilez le code :**
    ```
    ./gradlew desktop:dist
    ```

5. **Exécutez le jeu :**
    ```
    cp desktop/build/libs/desktop-1.0.jar ./
    java -jar desktop-1.0.jar
    ```

## Contrôles du jeu

- Utilisez les touches fléchées pour déplacer la caméra.
- Cliquez sur les icônes des tours pour les sélectionner.
- Placez les tours sur le terrain en cliquant à l'emplacement souhaité.

## Fonctionnalités

- **Tower Defense 2D :** Protégez votre base des vagues d'ennemis en plaçant stratégiquement des tours le long du chemin.

- **Variété de tours :** Choisissez parmi différentes tours, chacune avec ses propres fonctionnalités.

- **Niveaux de difficulté :** Affrontez des ennemis de plus en plus forts et ajustez la difficulté en fonction de votre expérience.

- **Resolution de la fenêtre :** Changer la resolution de la fenêtre à la taille que vous le souhaiter.

- **Musique réglable :** Choisisser le volume de la musique grace à un curseur. Ou mutez le pour ne plus avoir de musique.

## Contribuer

Si vous souhaitez contribuer à l'amélioration de Factory, n'hésitez pas à créer une pull request ou à signaler des problèmes sur [le dépôt GitHub](https://github.com/votre-utilisateur/factory).

## Remerciements

Un grand merci à tous les contributeurs qui ont rendu ce projet possible.

--

Particulièrement Corentin Labrux et Evan Michel qui ont participé au developpement du projet
