# Projet 10 : Amèliorer OcBiblio
 Une version amélioré du projet Ocbiblio avec de nouvelles 
features, correction d'un bug et mis en place de test.
https://github.com/Moorgtat/OCbiblioV2.git

# Features
* Site Web Responsive 
* Connexion utilisateur
* Recherche de Livres Multicritères
* Ajout de Livre / Dl Couvertures
* Gestion de Prêt de Livre
* Limite de Prêt de livre
* Limite de Prolongation de Prêt
* Simulation Remise Livres
 
# Technologies utilisées
Architecture MICROSERVICE. Projet basé autour de l'écosystème 
SPRING avec une UI développée sous le framework Javascript 
Vue.JS.

* Spring CLOUD, EUREKA Discovery, ZUUL
* Spring BOOT
* Spring DATA / JPA / HIBERNATE
* VUE.JS
* Material Design 
* MySQL (WorkBench)
* Axios

# Installation
Réaliser un gitclone du projet dans votre répertoire de travail à 
partir de ce lien : "https://github.com/Moorgtat/OcBiblio.git". 

L'importer dans IntelliJ Idea.

Executer le script SQL fourni via un editeur SQL. 

La BDD est créée ainsi que les tables du projet.

Modifier le fichier application.properties à la racine du projet:
"spring.datasource.url=" => Entrez le lien vers la BDD créée 
"spring.datasource.username=" => Entrez votre username 
"spring.datasource.password=" => Entrez votre Mot de passe

Démarrez chaque microservices ainsi que L'UI avec la commande 
"npm run dev".

# Se connecter
* Nom d'utilisateur : Mathieu Mdp: 1111 
* Nom d'utilisateur : Elorna Mdp: 2222
* Pour se connecter avec d'autres utilisateur se référer à la table 
"user" dans la Bdd.

# Auteur
Mathieu Le Cardinal dans le cadre de sa formation developpeur JAVA 
chez OpenClassroom.