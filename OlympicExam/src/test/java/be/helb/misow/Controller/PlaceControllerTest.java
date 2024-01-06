package be.helb.misow.Controller;

import be.helb.misow.Model.Place;
import be.helb.misow.Service.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// Classe de test pour PlaceController
@ExtendWith(MockitoExtension.class)
public class PlaceControllerTest {

    @Mock
    private PlaceService placeService; // Mock du service PlaceService

    @InjectMocks
    private PlaceController placeController; // Injection du mock dans PlaceController

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllPlace_thenPlacesShouldBeReturned() {
        // Arrange (Préparation)
        // Création d'une liste attendue de places (stades, par exemple)
        List<Place> expectedPlaces = Arrays.asList(
                new Place("Stadium1", "Address1", 50000),
                new Place("Stadium2", "Address2", 60000)
        );
        // Configuration du mock pour retourner la liste attendue
        when(placeService.getAllPlace()).thenReturn(expectedPlaces);

        // Act (Action)
        // Appel de la méthode du contrôleur pour obtenir toutes les places
        List<Place> actualPlaces = placeController.getAllPlace();

        // Assert (Vérification)
        // Vérification que la liste de places n'est pas nulle et a la bonne taille
        assertNotNull(actualPlaces);
        assertEquals(expectedPlaces.size(), actualPlaces.size());
        // Vérification que le service a bien été appelé
        verify(placeService).getAllPlace();
    }

    @Test
    public void whenAddPlace_thenPlaceShouldBeAdded() {
        // Arrange
        // Création d'une nouvelle place à ajouter
        Place newPlace = new Place("New Stadium", "New Address", 70000);

        // Act
        // Appel de la méthode du contrôleur pour ajouter une place
        placeController.addPlace(newPlace);

        // Assert
        // Vérification que la méthode addPlace du service a bien été appelée avec newPlace
        verify(placeService).addPlace(newPlace);
    }

    @Test
    public void whenDeletePlace_thenPlaceShouldBeDeleted() {
        // Arrange
        // Définition de l'identifiant de la place à supprimer
        Long placeId = 1L;

        // Act
        // Appel de la méthode du contrôleur pour supprimer une place
        placeController.deletePlace(placeId);

        // Assert
        // Vérification que la méthode deletePlaceById du service a bien été appelée avec placeId
        verify(placeService).deletePlaceById(placeId);
    }
}
