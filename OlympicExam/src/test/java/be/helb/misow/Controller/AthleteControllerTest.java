package be.helb.misow.Controller;

import be.helb.misow.Controller.AthleteController;
import be.helb.misow.Model.Athlete;
import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
import be.helb.misow.Service.AthleteService;
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

// Classe de test pour AthleteController
@ExtendWith(MockitoExtension.class)
public class AthleteControllerTest {

    @Mock
    private AthleteService athleteService; // Mock du service AthleteService

    @InjectMocks
    private AthleteController athleteController; // AthleteController avec le mock injecté

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllAthlete_thenAthletesShouldBeReturned() {
        // Arrange (Préparation)
        Sport sport1 = new Sport("100m", "Athlétisme");
        Country country1 = new Country("Belgique");
        List<Athlete> expectedAthletes = Arrays.asList(
                new Athlete("eric", 'M', 17, "Belge", sport1, country1),
                new Athlete("garcia", 'M', 17, "Belge", sport1, country1)
        );
        when(athleteService.getAllAthlete()).thenReturn(expectedAthletes);

        // Act (Action)
        List<Athlete> athletes = athleteController.getAllAthlete();

        // Assert (Vérification)
        assertNotNull(athletes); // Vérifie que la liste n'est pas nulle
        assertEquals(2, athletes.size()); // Vérifie que la taille de la liste est correcte
        verify(athleteService).getAllAthlete(); // Vérifie que la méthode getAllAthlete du service a été appelée
    }

    @Test
    public void whenAddAthlete_thenAthleteShouldBeAdded() {
        // Arrange
        Sport sport2 = new Sport("100m", "Athlétisme");
        Country country2 = new Country("Belgique");
        Athlete newAthlete = new Athlete("eric", 'M', 17, "Belge", sport2, country2);

        // Act
        athleteController.addAthlete(newAthlete);

        // Assert
        verify(athleteService).addAthlete(newAthlete); // Vérifie que la méthode addAthlete du service a été appelée avec newAthlete
    }

    @Test
    public void whenFindAthletesByAgeAndGender_thenCorrectAthletesShouldBeReturned() {
        // Arrange
        int age = 25;
        char gender = 'F';
        Sport sport = new Sport("Tennis", "Racquet Sports");
        Country country = new Country("USA");
        List<Athlete> expectedAthletes = Arrays.asList(new Athlete("Jane Doe", gender, age, "American", sport, country));

        // Mocking the service method
        when(athleteService.findAthletesByAgeAndGender(age, gender)).thenReturn(expectedAthletes);

        // Act
        List<Athlete> actualAthletes = athleteController.findAthletesByAgeAndGender(age, gender);

        // Assert
        assertNotNull(actualAthletes); // Check if the result is not null
        assertEquals(expectedAthletes, actualAthletes); // Check if the returned list matches the expected list
        verify(athleteService).findAthletesByAgeAndGender(age, gender); // Verify that the service method was called with correct parameters
    }

    @Test
    public void whenDeleteAthlete_thenAthleteShouldBeDeleted() {
        // Arrange
        Long athleteId = 1L; // Identifiant de l'athlète à supprimer

        // Act
        athleteController.deleteAthlete(athleteId);

        // Assert
        verify(athleteService).deleteAthleteById(athleteId); // Vérifie que la méthode deleteAthleteById du service a été appelée avec athleteId
    }
}
