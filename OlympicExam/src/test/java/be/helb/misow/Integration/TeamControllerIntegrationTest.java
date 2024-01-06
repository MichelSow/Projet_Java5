package be.helb.misow.Integration;

import be.helb.misow.Dao.CountryRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Dao.TeamRepository;
import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
import be.helb.misow.Model.Team;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Classe de test d'intégration pour TeamController.
 * Ces tests vérifient les fonctionnalités de l'API liées aux équipes en utilisant RestAssured.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port utilisé par le serveur de test

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    SportRepository sportRepository;

    @Autowired
    CountryRepository countryRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration de RestAssured pour utiliser le port du serveur de test
    }

    @Test
    public void whenGetAllTeam_thenStatus200() {
        // Test pour vérifier que la requête GET /getAllTeam renvoie une liste d'équipes
        given()
                .when()
                .get("/getAllTeam") // Envoi d'une requête GET à l'endpoint
                .then()
                .statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des équipes
    }

    @Test
    public void whenAddTeam_thenStatusIsOk() {
        // Création et sauvegarde des objets nécessaires pour le test
        Sport sport = new Sport("BOB", "Team Sport");
        sport = sportRepository.save(sport);

        Country country = new Country("Land");
        country = countryRepository.save(country);

        Team team = new Team("naruto", sport, country); // Création d'une nouvelle équipe

        // Envoi de la requête POST pour ajouter l'équipe via l'endpoint API
        given()
                .contentType(ContentType.JSON)
                .body(team)
                .when()
                .post("/addTeam")
                .then()
                .statusCode(200);


        // je supprime les objets creé pour le test

        Team savedTeam = teamRepository.findByName("naruto");
        Country savedCountry = countryRepository.findByName("Land");
        Sport savedSport = sportRepository.findByName("BOB");
        if (savedTeam != null) {
            teamRepository.deleteById(savedTeam.getId());
            countryRepository.deleteById(savedCountry.getId());
            sportRepository.deleteById(savedSport.getId());
        }

    }


    @Test
    public void whenDeleteTeam_thenStatusIsOk() {
        // Création et sauvegarde d'une nouvelle équipe dans la base de données
        Sport sport = new Sport("fa", "Team Sport");
        sport = sportRepository.save(sport);
        Country country = new Country("fz");
        country = countryRepository.save(country);

        Team team = new Team("Brother", sport, country); // Création d'une nouvelle équipe
        team = teamRepository.save(team);
        Long idTeam = team.getId();

        // Test pour supprimer l'équipe créée précédemment et vérifier le statut de la réponse
        given()
                .when()
                .delete("/deleteTeam/" + idTeam)
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)

        // je supprime les objets creé pour le test
        Country savedCountry = countryRepository.findByName("fz");
        Sport savedSport = sportRepository.findByName("fa");
        countryRepository.deleteById(savedCountry.getId());
        sportRepository.deleteById(savedSport.getId());
    }
}
