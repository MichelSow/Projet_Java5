package be.helb.misow.Integration;

import be.helb.misow.Dao.AthleteRepository;
import be.helb.misow.Dao.CountryRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Model.Athlete;
import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
import be.helb.misow.Service.AthleteService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Classe de test d'intégration pour AthleteController.
 * Ces tests vérifient le comportement de l'API en utilisant RestAssured.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AthleteControllerIntegrationTest {

    @Autowired
    SportRepository sportRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    AthleteRepository athleteRepository;
    @Autowired
    AthleteService athleteService;

    @LocalServerPort
    private int port; // Port utilisé par le serveur de test

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration de RestAssured pour utiliser le port du serveur de test
    }

    @Test
    public void whenGetAllAthlete_thenStatus200AndAthletesReturned() {
        // Test pour vérifier que la requête GET /getAllAthlete renvoie une liste d'athlètes
        given()
                .when().get("/getAllAthlete") // Envoi d'une requête GET à l'endpoint
                .then().statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des athlètes
    }

    @Test
    public void whenAddAthlete_thenStatusIsOk() {
        // Préparation des données pour le test
        Sport sport = new Sport("500", "Team Sport");
        sport = sportRepository.save(sport);
        Country country = new Country("Danemark");
        country = countryRepository.save(country);

        Athlete athlete = new Athlete("blabla", 'M', 32, "USA", sport, country); // Création de l'objet Athlete

        // Envoi de la requête POST pour ajouter l'athlète via l'endpoint API
        given()
                .contentType(ContentType.JSON)
                .body(athlete)
                .when()
                .post("/addAthlete")
                .then()
                .statusCode(HttpStatus.OK.value());

        // Récupération de l'athlète pour obtenir son ID et nettoyer la base de données pour la remettre par défaut
        Athlete savedAthlete = athleteRepository.findByName("blabla");
        if (savedAthlete != null) {
            athleteRepository.deleteById(savedAthlete.getId());
        }
        countryRepository.deleteById(country.getId());
        sportRepository.deleteById(sport.getId());
    }


    @Test
    public void whenDeleteAthlete_thenStatusIsOk() {
        // Test pour supprimer un athlète et vérifier le statut de la réponse
        Sport sport = sportRepository.findByName("100m");
        Country country = countryRepository.findByName("Belgium");
        Athlete athlete = new Athlete("Tarzan", 'M', 30, "USA", sport, country); // Création d'un nouvel athlète
        athlete = athleteRepository.save(athlete);
        Long idAthlete = athlete.getId();

        given()
                .when().delete("/deleteAthlete/" + idAthlete) // Envoi d'une requête DELETE à l'endpoint
                .then().statusCode(HttpStatus.OK.value()); // Vérification que le statut HTTP est 200 (OK)
    }

    @Test
    public void whenFindAthletesByAgeAndGender_thenStatus200AndCorrectAthletesReturned() {
        // Test pour récupérer les athlètes par âge et genre et vérifier le statut de la réponse
        Sport sport = sportRepository.findByName("100m");
        Country country = countryRepository.findByName("Belgium");
        Athlete athlete = new Athlete("tartine", 'F', 25, "USA", sport, country); // Création d'un nouvel athlète
        athlete = athleteRepository.save(athlete);


        int age = 25;
        char gender = 'F';

        given()
                .param("age", age)
                .param("gender", gender)
                .when().get("/findAthletes")
                .then().statusCode(200)
                .body("$", hasSize(greaterThan(0))); // Vérifie que la réponse contient des athlètes correspondant aux critères

        // supprime l'entité creé et remet la db par défaut
        Athlete savedAthlete = athleteRepository.findByName("tartine");
        if (savedAthlete != null) {
            athleteRepository.deleteById(savedAthlete.getId());
        }
    }
}
