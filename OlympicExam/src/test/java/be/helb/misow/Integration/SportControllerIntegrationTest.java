package be.helb.misow.Integration;

import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Model.Sport;
import be.helb.misow.Service.SportService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Classe de test d'intégration pour SportController.
 * Ces tests vérifient les fonctionnalités de l'API liées aux sports en utilisant RestAssured.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SportControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port utilisé par le serveur de test

    @Autowired
    SportRepository sportRepository;
    @Autowired
    SportService sportService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration de RestAssured pour utiliser le port du serveur de test
    }

    @Test
    public void whenGetAllSport_thenStatus200() {
        // Test pour vérifier que la requête GET /getAllSport renvoie une liste de sports
        given()
                .when()
                .get("/getAllSport") // Envoi d'une requête GET à l'endpoint
                .then()
                .statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des sports
    }

    @Test
    public void whenAddSport_thenStatusIsOk() {
        // Test pour ajouter un sport et vérifier le statut de la réponse
        Sport sport = new Sport("Baseball", "Team Sport"); // Création d'un nouveau sport

        given()
                .contentType("application/json") // Définition du type de contenu de la requête
                .body(sport) // Ajout du sport au corps de la requête
                .when()
                .post("/addSport") // Envoi d'une requête POST à l'endpoint
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)

        // Suppression du sport après le test pour nettoyer la base de données
        sportService.deleteSportByName(sport.getName());
    }

    @Test
    public void whenDeleteSport_thenStatusIsOk() {
        // Création et sauvegarde d'un nouvel objet sport dans la base de données
        Sport sport = new Sport("Basket", "Team Sport");
        sport = sportRepository.save(sport);
        Long sportId = sport.getId(); //

        // Test pour supprimer le sport créé précédemment et vérifier le statut de la réponse
        given()
                .when()
                .delete("/deleteSport/" + sportId) // Envoi d'une requête DELETE à l'endpoint
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)
    }
}
