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
 * Classe de test d'intégration pour MedalController.
 * Ces tests permettent de s'assurer que les endpoints liés aux médailles fonctionnent correctement.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedalControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port utilisé par le serveur de test

    @Autowired
    MedalService medalService;

    @Autowired
    MedalRepository medalRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    SportRepository sportRepository;

    @Autowired
    SportService sportService;

    @Autowired
    AthleteRepository athleteRepository;

    @Autowired
    AthleteService athleteService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration de RestAssured pour utiliser le port du serveur de test
    }

    @Test
    public void whenGetAllMedal_thenStatus200() {
        // Test pour vérifier que la requête GET /getAllMedal renvoie une liste de médailles
        given()
                .when()
                .get("/getAllMedal") // Envoi d'une requête GET à l'endpoint
                .then()
                .statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des médailles
    }

    @Test
    public void whenAddMedal_thenStatusIsOk() {
        // Préparation des données pour le test
        Sport sport = new Sport("nar", "Team Sport");
        sport = sportRepository.save(sport);

        Country country = new Country("digi");
        country = countryRepository.save(country);

        Athlete athlete = new Athlete("Yvi", 'M', 30, "USA", sport, country);
        athlete = athleteRepository.save(athlete);

        Team team = new Team("Car", sport, country);
        team = teamRepository.save(team);

        Medal medal = new Medal("blam", sport, athlete, team); // Création de l'objet Medal

        // Envoi de la requête POST pour ajouter la médaille via l'endpoint API
        given()
                .contentType("application/json")
                .body(medal)
                .when()
                .post("/addMedal")
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)

        // Récupération des entité creé nettoyer la base de données
        Medal savedMedal = medalRepository.findByType("blam");
        Team savedTeam = teamRepository.findByName("Car");
        Athlete savedAthlete = athleteRepository.findByName("Yvi");
        Country savedCountry = countryRepository.findByName("digi");
        Sport savedSport = sportRepository.findByName("nar");
        if (savedMedal != null) {
            medalRepository.deleteById(savedMedal.getId());
            teamRepository.deleteById(savedTeam.getId());
            athleteRepository.deleteById(savedAthlete.getId());
            countryRepository.deleteById(savedCountry.getId());
            sportRepository.deleteById(savedSport.getId());
        }


    }


    @Test
    public void whenDeleteMedal_thenStatusIsOk() {
        // Test pour supprimer une médaille et vérifier le statut de la réponse
        // Création des objets nécessaires pour ajouter puis supprimer une médaille
        Sport sport = new Sport("fa", "Team Sport");
        sport = sportRepository.save(sport);
        Country country = new Country("fz");
        country = countryRepository.save(country);
        Athlete athlete = new Athlete("fe", 'M', 30, "USA", sport, country);
        athlete = athleteRepository.save(athlete);
        Team team = new Team("fr", sport, country);
        team = teamRepository.save(team);
        Medal medal = new Medal("ft", sport, athlete, team); // Création d'une nouvelle médaille
        medal = medalRepository.save(medal);
        Long idMedal = medal.getId();

        // Envoi de la requête DELETE pour supprimer la médaille
        given()
                .when()
                .delete("/deleteMedal/" + idMedal)
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)


        // supprime les entités creé pour remettre la db par défaut
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
