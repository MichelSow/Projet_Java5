package be.helb.misow.Integration;

import be.helb.misow.Model.Sponsor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//TODO corriger cette classe

// Classe de test d'intégration pour SponsorController

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SponsorIntegrationTest {


    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }



    // Teste la récupération de tous les sponsors et vérifie si il y a plus d'1 élément
    @Test
    public void testGetAllSponsors() {
        given()
                .when()
                .get("/sponsors")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThan(0)));
    }

    // Teste l'ajout d'un nouveau sponsor
    @Test
    public void testAddSponsor() {
        given()
                .contentType(ContentType.JSON)
                .body(new Sponsor("pepsi"))
                .when()
                .post("/sponsors")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    // Teste la récupération d'un sponsor par son ID
    @Test
    public void testGetSponsorById() {
        int sponsorId = 1;
        given()
                .when()
                .get("/sponsors/" + sponsorId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(sponsorId));
    }

    // Teste la récupération d'un sponsor qui n'existe pas
    @Test
    public void testGetSponsorNotFound() {
        long sponsorId = 99999;
        given()
                .when()
                .get("/sponsors/" + sponsorId)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Teste la suppression d'un sponsor
    @Test
    public void testDeleteSponsor() {
        long sponsorId = 5;
        given()
                .when()
                .delete("/sponsors/" + sponsorId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    // Teste la liste des sponsors après une suppression
    @Test
    public void testSponsorListAfterDeletion() {
        long sponsorId = 3;
        given()
                .when()
                .delete("/sponsors/" + sponsorId);

        given()
                .when()
                .get("/sponsors")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", not(hasItem(hasProperty("id", equalTo(sponsorId)))));
    }
}
