package be.helb.misow.Service;

import be.helb.misow.Dao.PlaceRepository;
import be.helb.misow.Model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository; // Mock du repository Place

    @InjectMocks
    private PlaceService placeService; // Service Place avec le mock injecté

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    // Test pour ajouter un lieu
    @Test
    public void whenAddPlace_thenPlaceShouldBeSaved() {
        Place place = new Place("Stade", "1234 Street", 5000.0f);
        placeService.addPlace(place); // Appel de la méthode pour ajouter un lieu
        verify(placeRepository).save(place); // Vérification que la méthode save du repository a été appelée
    }

    // Test pour supprimer un lieu
    @Test
    public void whenDeletePlace_thenPlaceShouldBeDeleted() {
        Long placeId = 1L; // ID du lieu à supprimer
        placeService.deletePlaceById(placeId); // Appel de la méthode pour supprimer un lieu
        verify(placeRepository).deleteById(placeId); // Vérification que la méthode deleteById du repository a été appelée
    }

    // Test pour obtenir tous les lieux
    @Test
    public void whenGetAllPlace_thenAllPlacesShouldBeReturned() {
        List<Place> expectedPlaces = Arrays.asList(new Place("Stade1", "1234 Street", 10000.0f),
                new Place("Stade2", "5678 Avenue", 15000.0f));
        when(placeRepository.findAll()).thenReturn(expectedPlaces); // Configuration du comportement du mock
        List<Place> actualPlaces = placeService.getAllPlace(); // Obtention des lieux
        assertEquals(expectedPlaces, actualPlaces); // Vérification que les lieux attendus et actuels sont les mêmes
        verify(placeRepository).findAll(); // Vérification que la méthode findAll du repository a été appelée
    }

    @Test
    public void testUpdatePlaceName() {
        // Test de la méthode updatePlaceName
        Long id = 1L; // Définir un ID de lieu pour le test
        String newName = "NouveauNom"; // Définir un nouveau nom pour le test
        Place place = new Place(); // Créer une nouvelle instance de Place
        place.setId(id); // Affecter l'ID à l'instance de Place

        // Configurer le mock pour retourner l'instance de Place lors de la recherche par ID
        when(placeRepository.findById(id)).thenReturn(Optional.of(place));

        placeService.updatePlaceName(id, newName); // Appeler updatePlaceName avec l'ID et le nouveau nom

        // Vérifier que la méthode findById a été appelée
        verify(placeRepository, times(1)).findById(id);
        // Vérifier que le nom du lieu a été mis à jour
        assert place.getName().equals(newName);
        // Vérifier que la méthode save a été appelée pour enregistrer les changements
        verify(placeRepository, times(1)).save(place);
    }

}
