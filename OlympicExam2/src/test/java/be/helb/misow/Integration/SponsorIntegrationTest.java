package be.helb.misow.Integration;

import be.helb.misow.Dao.SponsorRepository;
import be.helb.misow.Model.Sponsor;
import be.helb.misow.Service.SponsorService;
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



// Classe de test d'intégration pour SponsorController

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SponsorIntegrationTest {


    @LocalServerPort
    private int port;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private SponsorRepository sponsorRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void testGetAllSponsors() {
        // Test pour obtenir tous les sponsors
        given()
                .when()
                .get("/sponsors/getAllSponsors")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)));
    }

    @Test
    public void testAddSponsor() {
        // Création d'un nouveau sponsor pour le test
        Sponsor newSponsor = new Sponsor("TestSponsor5");



        // Ajout du sponsor
        given()
                .contentType(ContentType.JSON)
                .body(newSponsor)
                .when()
                .post("/sponsors/addSponsor")
                .then()
                .statusCode(201);

        // suppression de l'objet creé après le test
        Sponsor savedSponsor = sponsorRepository.findByName("TestSponsor5");
        sponsorRepository.deleteById(savedSponsor.getId());
    }

    @Test
    public void testDeleteSponsor() {

        Sponsor newSponsor = new Sponsor("TestSponsor2");
        newSponsor = sponsorRepository.save(newSponsor);
        Long idSponsor = newSponsor.getId();
        // Suppression du sponsor
        given()
                .when()
                .delete("/sponsors/deleteSponsor/" + idSponsor)
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindSponsorById() {
        Sponsor newSponsor = new Sponsor("TestSponsor8");
        newSponsor = sponsorRepository.save(newSponsor);
        Long sponsorId = newSponsor.getId();

        given()
                .when()
                .get("/sponsors/findSponsor/" + sponsorId)
                .then()
                .statusCode(200)
                .body("id", equalTo(sponsorId.intValue()));

        Sponsor savedSponsor = sponsorRepository.findByName("TestSponsor8");
        sponsorRepository.deleteById(savedSponsor.getId());
    }
}
