package be.helb.misow.Integration;

import be.helb.misow.Dao.PlaceRepository;
import be.helb.misow.Model.Place;
import be.helb.misow.Service.PlaceService;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Classe de test d'intégration pour PlaceController.
 * Ces tests vérifient les fonctionnalités de l'API liées aux lieux en utilisant RestAssured.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaceControllerIntegrationTest {

    @LocalServerPort
    private int port; // Port utilisé par le serveur de test

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceService placeService;

    @BeforeEach
    public void setUp() {

        RestAssured.port = port; // Configuration de RestAssured pour utiliser le port du serveur de test
        RestAssured.defaultParser = Parser.JSON; // Définir le parseur par défaut en cas de manque de Content-Type
    }

    @Test
    public void whenGetAllPlace_thenStatus200() {
        // Test pour vérifier que la requête GET /getAllPlace renvoie une liste de lieux
        given()
                .when()
                .get("/getAllPlace") // Envoi d'une requête GET à l'endpoint
                .then()
                .statusCode(200) // Vérification que le statut HTTP est 200 (OK)
                .body("$", hasSize(greaterThan(0))); // Vérification que le corps de la réponse contient des lieux
    }

    @Test
    public void whenAddPlace_thenStatusIsOk() {
        // Test pour ajouter un lieu et vérifier le statut de la réponse
        Place place = new Place("New Stadium", "New Address", 80000); // Création d'un nouveau lieu
        place = placeRepository.save(place);
        Long idPlace = place.getId();

        given()
                .contentType("application/json") // Définition du type de contenu de la requête
                .body(place) // Ajout du lieu au corps de la requête
                .when()
                .post("/addPlace") // Envoi d'une requête POST à l'endpoint
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)

        // Suppression du lieu après le test pour nettoyer la base de données
        placeService.deletePlaceById(idPlace);
    }

    @Test
    public void whenDeletePlace_thenStatusIsOk() {
        // Test pour supprimer un lieu et vérifier le statut de la réponse
        Place place = new Place("New Stadium", "New Address", 80000); // Création d'un nouveau lieu
        place = placeRepository.save(place);
        Long idPlace = place.getId();

        given()
                .when()
                .delete("/deletePlace/" + idPlace) // Envoi d'une requête DELETE à l'endpoint
                .then()
                .statusCode(200); // Vérification que le statut HTTP est 200 (OK)
    }


    @Test
    public void whenUpdatePlaceName_thenStatusIsOk() {
        // Test pour mettre à jour le nom d'un lieu et vérifier le statut de la réponse
        Place place = new Place("Old", "Some Address", 50000); // Création d'un nouveau lieu avec un ancien nom
        place = placeRepository.save(place); // Sauvegarde du lieu dans la base de données
        Long idPlace = place.getId(); // Récupération de l'ID du lieu sauvegardé

        String newName = "Updated Name"; // Définir un nouveau nom pour la mise à jour

        given()
                .contentType("application/json") // Définition du type de contenu de la requête
                .queryParam("newName", newName) // Ajout du paramètre de requête pour le nouveau nom
                .when()
                .patch("/updatePlaceName/" + idPlace) // Envoi d'une requête PATCH à l'endpoint
                .then()
                .statusCode(200);// Vérification que le statut HTTP est 200 (OK)



        // Suppression du lieu après le test pour nettoyer la base de données
        placeService.deletePlaceById(idPlace);
    }

}
