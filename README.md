# Projet jeux olympic

Introduction 
--------------------
Cette anneé pour le projet de Java 5, il nous a été demander de réaliser le développement d'API Web multicouche avec Spring Boot.

Contexte : Vous agirez en tant que prestataire pour un client déterminé, devant réaliser deux API Web
multicouches utilisant le framework Spring Boot. Le projet a été validé et implique la création d'une API
principale et d'une API secondaire, cette dernière étant limitée à une seule entité. L'API principale devra
communiquer avec l'API secondaire pour récupérer des informations.

Theme choisi
--------------------
Les jeux olympiques 

Modèles
--------------------
 Api 1 : Athlete, Country, Medal, Place, Result, Sponsor, Sport, Team  
 Api 2 : Sponsor

Relations 
--------------------
Api 1 ==>

- Athlete

@ManyToOne vers Country: Un athlète appartient à un seul pays.  
@ManyToOne vers Sport: Un athlète pratique un seul sport.    
@ManyToMany vers Team: Un athlète peut appartenir à plusieurs équipes.

- Country

@OneToMany vers Athlete: Un pays peut avoir plusieurs athlètes.  
@OneToMany vers Team: Un pays peut avoir plusieurs équipes.

- Medal

@ManyToOne vers Sport: Une médaille est associée à un seul sport.  
@ManyToOne vers Athlete: Une médaille est gagnée par un seul athlète.  
@ManyToOne vers Team: Une médaille est gagnée par une seule équipe.

- Place

Pas de relation directe avec les autres classes.  

- Result

@ManyToOne vers Athlete: Un résultat est associé à un seul athlète.  
@ManyToOne vers Team: Un résultat est associé à une seule équipe.  
@ManyToOne vers Sport: Un résultat est associé à un seul sport.  

- Sponsor

Pas de relation directe avec les autres classes.  

- Sport

Pas de relation directe avec les autres classes, mais il est lié à Athlete, Team, et Medal via des relations @ManyToOne.  

- Team

@ManyToOne vers Sport: Une équipe pratique un seul sport.  
@ManyToOne vers Country: Une équipe représente un seul pays.  
@ManyToMany vers Athlete: Une équipe est composée de plusieurs athlètes.  
@OneToMany vers Result: Une équipe peut avoir plusieurs résultats.

Api 2 ==>

- Sponsor

Pas de relation directe avec les autres classes.

Technologie Utilisé 
---------------------
* Base de donneé
PostGreSql
Test des requetes ==> Postman  

Dépendance utilisé
-------------------
Lomboak ce qui permet de...
Rest Asured ce qui permet de ....

Fonctionnalité disponible 
---------------------
* Recevoir une liste d'athlete qui particpe au jeux olympique 




