package be.helb.misow.Integration;

import be.helb.misow.Dao.CountryRepository;
import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
import be.helb.misow.Service.CountryService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

// Classe de test d'intégration pour CountryController
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port sur lequel le serveur de test s'exécute

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryService countryService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port; // Configuration du port pour RestAssured
    }

    @Test
    public void testGetAllCountry() {
        // Test pour obtenir tous les pays
        given()
                .when()
                .get("/getAllCountry") // Envoi d'une requête GET à l'endpoint
                .then()
                .statusCode(HttpStatus.OK.value()) // Vérification que le statut HTTP est 200 (OK)
                .contentType(ContentType.JSON) // Vérification que le contenu est au format JSON
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des données
    }

    // Test pour ajouter un pays
    @Test
    public void testAddCountry() {

        Country newCountry = new Country("Brazil"); // Création d'un nouveau pays
        newCountry = countryRepository.save(newCountry);
        Long idCountry = newCountry.getId();
        given()
                .contentType(ContentType.JSON) // Définition du type de contenu de la requête
                .body(newCountry) // Ajout du pays au corps de la requête
                .when()
                .post("/addCountry") // Envoi d'une requête POST à l'endpoint
                .then()
                .statusCode(HttpStatus.OK.value()); // Vérification que le statut HTTP est 200 (OK)

        // je supprime le pays a la fin du test pour ne pas modifier ma DB
        countryService.deleteCountryById(idCountry);
    }

    // test de suppression d'un pays
    @Test
    public void whenDeleteCountry() {
        // ici je cree un nouvelle objet test que je save dans ma DB
        Country country = new Country("Danemark");
        country = countryRepository.save(country);
        Long sportId = country.getId();


        // et la je supprime l'objet que j'ai creé au dessus pour ne pas modifier ma DB
        given()
                .when()
                .delete("/deleteCountry/" + sportId) // Envoi d'une requête DELETE à l'endpoint
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)
    }

}
