# Projet jeux olympiques

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
* Base de donneé ==> PostGreSql
username=postgres
password=Helb1.1

* Test des requetes ==> Postman  

Dépendance utilisé
-------------------
* Lomboak

  Utilisé pour réduire le code de plomberie en Java, comme les getters, setters

* PostgreSQL Driver

  Le driver JDBC pour la base de données PostgreSQL

* MapStruct

  Un générateur de code pour les conversions entre types Java beans

* Spring Boot Starter Web

  Fournit tous les éléments nécessaires pour construire des applications web avec Spring, y compris RESTful applications using Spring MVC

* Spring Boot Starter Data JPA

  Fournit les éléments nécessaires pour utiliser Spring Data JPA avec Hibernate

* Rest-Assured

  Utilisé pour tester et valider les services REST en Java

* Spring Boot Starter Test

  Inclut les dépendances nécessaires pour tester les applications Spring Boot

* Feign OkHttp

  Un client HTTP qui peut être utilisé avec Feign pour effectuer des appels HTTP

* Feign Jackson

  Intègre Jackson pour la sérialisation et la désérialisation des objets dans les appels Feign

Fonctionnalité disponible 
---------------------
! Certaines entités sont reliés entre elles, il faut donc supprimer les enfants puis les parents !  

Api 1  

1)
* Recevoir une liste d'athlete qui particpe au jeux olympique

  http://localhost:8080/getAllAthlete
  
* Ajouter un athlete

  http://localhost:8080/addAthlete
  
  {
  
      "name": "Michel",
      "gender": "H",
      "age": 20,
      "nationality": "Belge",
      "team_id": 19
      "country_id": 1
  
  }
  
* Supprimer un athlete

  http://localhost:8080/deleteAthlete/33
  
* Rechecher un athlete

  http://localhost:8080/findAthletes?age=20&gender=H

2) 
* Recevoir une liste de pays qui sont representer au jeux olympique

  http://localhost:8080/getAllCountry
  
* Ajouter un athlete

  http://localhost:8080/addCountry
  {
        
        "name": "Espagne",
       
        
    }
  
* Supprimer un athlete

  http://localhost:8080/deleteCountry/1

3)
* Recevoir une liste de Medaille qui ont été distribuer au jeux olympique

  http://localhost:8080/getAllMedal
  
* Ajouter une medaille

  http://localhost:8080/addMedal
  {
        
        "type": "Bronze",
        "athlete_id": 33,
        "sport_id": 1,
        "team_id": 19
       
        
    }
  
* Supprimer une medaille

  http://localhost:8080/deleteMedal/8

4) 
* Recevoir une liste de place où se dérouleront les jeux olympique

  http://localhost:8080/getAllPlace
  
* Ajouter une Place

  http://localhost:8080/addPlace
  {
        
        "adresse": "Rue des poest 19",
        "capacity": 33000,
        "name": "stade des martyres"
        
       
        
    }

* Modifier le nom d'une place

  http://localhost:8080/updatePlaceName/3?newName=Stade1
  
  
* Supprimer une place

  http://localhost:8080/deletePlace/1


5)

* Recevoir une liste de resultat pour les épreuves au jeux olympique

  http://localhost:8080/getAllResult
  
* Ajouter un resultat

  http://localhost:8080/addResult
  {
        
        "rank": 2,
        "score": 60,
        "athlete_id": 34,
        "sport_id": 1,
        "team_id": 19
       
        
    }
  
* Supprimer un résultat

  http://localhost:8080/deleteResult/9

6)
* Recevoir une liste de sport des jeux olympique

  http://localhost:8080/getAllSport
  
* Ajouter un Sport

  http://localhost:8080/addSport
  {
        
        "category": "Athlétisme",
        "name": "Lancer de javelot"
        
       
        
  }
  
* Supprimer un Sport

  http://localhost:8080/deleteSport/1

7)  

* Recevoir une liste des équipes qui participe aux épreuves des jeux olympique

  http://localhost:8080/getAllTeam
  
* Ajouter une team

  http://localhost:8080/addTeam
  {
        
        "name": "Big bros",
        "Country_id": 1,
        "sport_id": 1
       
        
    }
  
* Supprimer une team

  http://localhost:8080/deleteTeam/19

8)

* Recevoir une liste de sponsors qui sponsorise les jeux olympiques ( Via la première Api )

  http://localhost:8080/olympic2/getAllSponsors


Api 2

1) 

* Recevoir une liste de sponsors qui sponsorise les jeux olympiques 

 http://localhost:8081/sponsors/getAllSponsors
  
* Ajouter un sponsor

  http://localhost:8081/sponsors/addSponsor
  {
        
        "name": "sponsor 1"
        
       
        
    }

* Rechercher un sponsor avec son ID en parametre

  http://localhost:8081/sponsors/findSponsor/1
  
* Supprimer un sponsor

  http://localhost:8081/sponsors/deleteSponsor/2
  
Tests
-------------------------
Api 1 Et Api 2  

Chaques Controlleur possède :  

* un test unitaire ( Toutes les methodes du controlleurs sont tester )
* un test d'intégration ( toutes les methodes du controlleurs sont également tester )

Chaques service possède :

* un test unitaire ( Toutes les methodes du service sont tester )

Ma classe de client ( DataAccessSponsor ) possède également une classe de test

Fin 
------------------------

Merci beaucoup de nous avoir encadré cette année ! 
C'était très enrichissant !
  




