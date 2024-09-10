# Jeu de Poker - Application Java

## Description

Bienvenue dans l'application de Poker développée en Java ! Ce projet a été réalisé dans le cadre d'un exercice personnel visant à acquérir une expérience plus approfondie en programmation orientée objet (POO). L'objectif principal était de convertir une programmation de base en utilisant les Collections, les Streams, les enums, et d'autres fonctionnalités avancées introduites à partir de Java 8.

> **Note** : Les commentaires et la documentation Javadoc sont en français, tandis que les noms des variables et des méthodes sont en anglais. Cette approche a été intentionnellement choisie pour s'adapter aux exigences.

## Fonctionnalités Clés

1. **Gestion des Combinaisons de Poker**  
   L'application évalue les mains de poker et détermine les gagnants en se basant sur les combinaisons classiques (pair, brelan, suite, couleur, etc.). Chaque combinaison est associée à une priorité, avec les combinaisons les plus fortes ayant une priorité plus élevée (comme la quinte flush royale).

2. **Résolution des Égalités (Tie)**  
   Lorsque deux joueurs ont des mains de force égale, une classe dédiée, `TieResolver`, est utilisée pour résoudre ces égalités en suivant des règles spécifiques. Par exemple, en cas de quinte flush royale pour deux joueurs, le vainqueur est déterminé par la règle des couleurs, où les cœurs (♥) ont la priorité sur les autres.

3. **Visualisation des Mains**  
   L'application se concentre sur la visualisation des mains de chaque joueur pour déterminer rapidement la combinaison gagnante. Seules les mains des joueurs sont affichées, ce qui permet une identification immédiate du vainqueur sans avoir à suivre un déroulement complet du jeu de poker.

4. **Utilisation de Lombok**  
   Lombok est largement utilisé pour réduire le code boilerplate, rendant ainsi le code plus propre et plus facile à maintenir. Les getters, setters, et autres méthodes utiles sont générés automatiquement grâce à des annotations Lombok.

5. **Structure Modulaire et Classe Intermédiaire `Hand`**  
   Le code est organisé de manière modulaire, séparant les responsabilités de manière claire :
    - `Game` gère le flux principal du jeu.
    - `TieResolver` est responsable de la résolution des égalités.
    - `HandEvaluator` évalue les différentes mains.

   De plus, une classe intermédiaire `Hand` a été introduite entre les cartes et les joueurs. Bien que cela ne soit pas nécessaire pour la fonctionnalité de base, cette structure a été retenue pour des raisons de logique, facilitant la gestion des cartes et des combinaisons, même si elle n'est pas la solution la plus pratique pour une implémentation simple.

6. **Utilisation des Collections, Streams, et Enums**  
   Ce projet a également été l'occasion d'explorer et de maîtriser les fonctionnalités modernes de Java, notamment :
    - **Collections** : Utilisation avancée pour gérer les listes de cartes et les mains des joueurs.
    - **Streams** : Manipulation fluide et fonctionnelle des données pour filtrer, trier, et collecter les résultats.
    - **Enums** : Définition des combinaisons et des couleurs de manière claire et structurée, facilitant la gestion des priorités et des règles du jeu.

7. **Gestion des Cartes et des Mains**  
   Chaque joueur dispose d'une main de cinq cartes, et ces mains sont évaluées pour déterminer la force relative des combinaisons. Le jeu prend également en charge la modification des valeurs de l'As pour gérer les suites basses (par exemple, A-2-3-4-5).

8. **Test Unitaire**  
   Des tests unitaires ont été mis en place pour garantir la fiabilité du jeu, y compris des cas de tests pour les égalités, la comparaison des cartes hautes et des priorités des combinaisons.

## Instructions d'Exécution

Pour exécuter l'application :

1. Cloner ce dépôt de code.
2. Compiler le projet avec Maven ou votre IDE préféré.
3. Exécuter la classe `Main` pour démarrer une nouvelle partie de poker.

## Exécution des Tests

Pour exécuter les tests unitaires, utilisez Maven ou l'environnement de test intégré de votre IDE :

mvn test

## Conclusion

Ce projet a été une excellente opportunité pour renforcer mes compétences en programmation orientée objet, tout en découvrant et en appliquant les nouvelles fonctionnalités offertes par Java à partir de la version 8. Que vous souhaitiez jouer à une partie de poker entre amis ou explorer la logique derrière les résolutions des égalités, cette application offre une base solide pour l'une ou l'autre de ces expériences.