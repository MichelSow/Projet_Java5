package be.helb.misow.Integration;

import be.helb.misow.Dto.SponsorDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// Classe de test d'intégration pour OlympicSponsorController
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OlympicSponsorControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port sur lequel le serveur de test s'exécute

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration du port pour RestAssured
    }

    @Test
    public void whenGetAllSponsors_thenStatus200() {
        // Test pour obtenir tous les sponsors
        given()
                .when()
                .get("/olympic2/getAllSponsors") // Envoi d'une requête GET à l'endpoint /olympic2/getAllSponsors
                .then()
                .statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des sponsors
    }

    
}
