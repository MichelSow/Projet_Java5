package be.helb.misow.Controller;

import be.helb.misow.Model.Sport;
import be.helb.misow.Service.SportService;
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

// Classe de test pour SportController
@ExtendWith(MockitoExtension.class)
public class SportControllerTest {

    @Mock
    private SportService sportService; // Mock du service SportService

    @InjectMocks
    private SportController sportController; // Injection du mock dans SportController

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllSport_thenSportsShouldBeReturned() {
        // Arrange (Préparation)
        // Création d'une liste attendue de sports
        List<Sport> expectedSports = Arrays.asList(
                new Sport("Football", "Team Sport"),
                new Sport("Tennis", "Individual Sport")
        );
        // Configuration du mock pour retourner la liste attendue
        when(sportService.getAllSport()).thenReturn(expectedSports);

        // Act (Action)
        // Appel de la méthode du contrôleur pour obtenir tous les sports
        List<Sport> actualSports = sportController.getAllSport();

        // Assert (Vérification)
        // Vérification que la liste de sports n'est pas nulle et a la bonne taille
        assertNotNull(actualSports);
        assertEquals(expectedSports.size(), actualSports.size());
        // Vérification que le service a bien été appelé
        verify(sportService).getAllSport();
    }

    @Test
    public void whenAddSport_thenSportShouldBeAdded() {
        // Arrange
        // Création d'un nouveau sport à ajouter
        Sport newSport = new Sport("Basketball", "Team Sport");

        // Act
        // Appel de la méthode du contrôleur pour ajouter un sport
        sportController.addSport(newSport);

        // Assert
        // Vérification que la méthode addSport du service a bien été appelée avec newSport
        verify(sportService).addSport(newSport);
    }

    @Test
    public void whenDeleteSport_thenSportShouldBeDeleted() {
        // Arrange
        // Définition de l'identifiant du sport à supprimer
        Long sportId = 1L;

        // Act
        // Appel de la méthode du contrôleur pour supprimer un sport
        sportController.deleteSport(sportId);

        // Assert
        // Vérification que la méthode deleteSportById du service a bien été appelée avec sportId
        verify(sportService).deleteSportById(sportId);
    }
}
