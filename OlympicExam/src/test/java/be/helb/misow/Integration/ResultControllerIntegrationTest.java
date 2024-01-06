package be.helb.misow.Integration;

import be.helb.misow.Dao.*;
import be.helb.misow.Model.*;
import be.helb.misow.Service.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Classe de test d'intégration pour ResultController.
 * Ces tests vérifient les fonctionnalités de l'API liées aux résultats en utilisant RestAssured.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port utilisé par le serveur de test

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ResultService resultService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    AthleteRepository athleteRepository;

    @Autowired
    AthleteService athleteService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    SportRepository sportRepository;

    @Autowired
    SportService sportService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration de RestAssured pour utiliser le port du serveur de test
    }

    @Test
    public void whenGetAllResult_thenStatus200() {
        // Test pour vérifier que la requête GET /getAllResult renvoie une liste de résultats
        given()
                .when()
                .get("/getAllResult") // Envoi d'une requête GET à l'endpoint
                .then()
                .statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des résultats
    }

    @Test
    public void whenAddResult_thenStatusIsOk() {
        // Création et sauvegarde des objets nécessaires pour le test
        Sport sport = new Sport("xx", "Team Sport");
        sport = sportRepository.save(sport);

        Country country = new Country("xc");
        country = countryRepository.save(country);

        Athlete athlete = new Athlete("xv", 'M', 30, "USA", sport, country);
        athlete = athleteRepository.save(athlete);

        Team team = new Team("xb", sport, country);
        team = teamRepository.save(team);

        Result result = new Result(athlete, team, sport, 0, 100);

        // Envoi de la requête POST pour ajouter le résultat via l'endpoint API
        given()
                .contentType("application/json")
                .body(result)
                .when()
                .post("/addResult")
                .then()
                .statusCode(200);

        // Nettoyage des entités créées
        Result savedResult = resultRepository.findByRank(0);
        Team savedTeam = teamRepository.findByName("xb");
        Athlete savedAthlete = athleteRepository.findByName("xv");
        Country savedCountry = countryRepository.findByName("xc");
        Sport savedSport = sportRepository.findByName("xx");
        if (savedResult != null) {
            resultRepository.deleteById(savedResult.getId());
            teamRepository.deleteById(savedTeam.getId());
            athleteRepository.deleteById(savedAthlete.getId());
            countryRepository.deleteById(savedCountry.getId());
            sportRepository.deleteById(savedSport.getId());
        }
    }


    @Test
    public void whenDeleteResult_thenStatusIsOk() {
        // Test pour supprimer un résultat et vérifier le statut de la réponse
        // Création des objets nécessaires pour ajouter puis supprimer un résultat
        Sport sport = new Sport("fa", "Team Sport");
        sport = sportRepository.save(sport);
        Country country = new Country("fz");
        country = countryRepository.save(country);
        Athlete athlete = new Athlete("fe", 'M', 30, "USA", sport, country);
        athlete = athleteRepository.save(athlete);
        Team team = new Team("fr", sport, country);
        team = teamRepository.save(team);
        Result result = new Result(athlete, team, sport, 2, 100); // Création d'un nouveau résultat
        result = resultRepository.save(result);
        Long idResult = result.getId();

        // Envoi de la requête DELETE pour supprimer le résultat
        given()
                .when()
                .delete("/deleteResult/" + idResult)
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)

        // je supprimer les objets creé pour le test
        Team savedTeam = teamRepository.findByName("fr");
        Athlete savedAthlete = athleteRepository.findByName("fe");
        Country savedCountry = countryRepository.findByName("fz");
        Sport savedSport = sportRepository.findByName("fa");

        teamRepository.deleteById(savedTeam.getId());
        athleteRepository.deleteById(savedAthlete.getId());
        countryRepository.deleteById(savedCountry.getId());
        sportRepository.deleteById(savedSport.getId());
    }
}
