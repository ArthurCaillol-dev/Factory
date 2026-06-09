# Factory
 
Bienvenue dans **Factory**, un jeu de tower defense 2D développé en Java avec LibGDX.
 
> **État du projet :** Version alpha fonctionnelle et jouable, réalisée en équipe de 3 dans un cadre scolaire (rôle : chef de projet). 
> **Limitation connue :** une fuite mémoire dégrade les performances après un temps de jeu prolongé (piste d'amélioration identifiée). L'interface graphique est également destinée à être retravaillée.
 
## Technologies
 
`Java` · `LibGDX` · `Gradle` · `Linux`
 
## Aperçu
 
<!-- Ajoute ici une capture d'écran ou un GIF du jeu :
![Aperçu de Factory](chemin/vers/capture.png)
-->
 
*(Capture d'écran à venir)*
 
## Instructions d'exécution
 
> **Prérequis :** Le projet a été pensé et développé sous Linux. Aucune implémentation n'a été faite pour Windows.
 
1. **Assurez-vous d'avoir Java installé sur votre machine.**
2. **Clonez le dépôt :**
   ```
   git clone git@github.com:ArthurCaillol-dev/Factory.git
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
 
- Utilisez les **touches fléchées** pour déplacer la caméra.
- **Cliquez** sur les icônes des tours pour les sélectionner.
- **Placez** les tours sur le terrain en cliquant à l'emplacement souhaité.
## Fonctionnalités
 
- **Tower Defense 2D :** Protégez votre base des vagues d'ennemis en plaçant stratégiquement des tours le long du chemin.
- **Variété de tours :** Choisissez parmi différentes tours, chacune avec ses propres caractéristiques.
- **Niveaux de difficulté :** Affrontez des ennemis de plus en plus forts et ajustez la difficulté selon votre expérience.
- **Résolution de la fenêtre :** Modifiez la résolution de la fenêtre à la taille souhaitée.
- **Musique réglable :** Ajustez le volume de la musique grâce à un curseur, ou coupez-la entièrement.
## Contribuer
 
Si vous souhaitez contribuer à l'amélioration de Factory, n'hésitez pas à ouvrir une *pull request* ou à signaler un problème via les [issues du dépôt](https://github.com/ArthurCaillol-dev/Factory/issues).

## Remerciements
 
Projet développé en équipe. Un grand merci aux contributeurs Corentin Labrux et Evan Michel qui ont participé au développement.
