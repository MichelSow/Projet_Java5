package be.helb.misow.Service;

import be.helb.misow.Dao.AthleteRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Model.Athlete;
import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
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

@ExtendWith(MockitoExtension.class)
public class AthleteServiceTest {

    @Mock
    private AthleteRepository athleteRepository; // Mock du repository Athlete

    @Mock
    private SportRepository sportRepository; // Mock du repository Sport (si nécessaire)

    @InjectMocks
    private AthleteService athleteService; // Service Athlete avec les mocks injectés

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenAddAthlete_thenAthleteShouldBeSaved() {
        // Test pour ajouter un athlète
        Athlete athlete = new Athlete("John Doe", 'M', 30, "USA", new Sport(), new Country());
        athleteService.addAthlete(athlete); // Ajout de l'athlète
        verify(athleteRepository).save(athlete); // Vérification que la méthode save du repository a été appelée
    }

    @Test
    public void whenDeleteAthlete_thenAthleteShouldBeDeleted() {
        // Test pour supprimer un athlète
        Long athleteId = 1L; // ID de l'athlète à supprimer
        athleteService.deleteAthleteById(athleteId); // Suppression de l'athlète
        verify(athleteRepository).deleteById(athleteId); // Vérification que la méthode deleteById du repository a été appelée
    }

    @Test
    public void whenGetAllAthlete_thenAllAthletesShouldBeReturned() {
        // Test pour obtenir tous les athlètes
        List<Athlete> expectedAthletes = Arrays.asList(new Athlete("John Doe", 'M', 30, "USA", new Sport(), new Country()));
        when(athleteRepository.findAll()).thenReturn(expectedAthletes); // Configuration du comportement du mock
        List<Athlete> actualAthletes = athleteService.getAllAthlete(); // Obtention des athlètes
        assertEquals(expectedAthletes, actualAthletes); // Vérification que les athlètes attendus et actuels sont les mêmes
        verify(athleteRepository).findAll(); // Vérification que la méthode findAll du repository a été appelée
    }

    @Test
    public void whenFindAthletesByAgeAndGender_thenCorrectAthletesShouldBeReturned() {
        // Test to find athletes by age and gender
        int age = 25;
        char gender = 'F';
        List<Athlete> expectedAthletes = Arrays.asList(new Athlete("Jane Doe", gender, age, "France", new Sport(), new Country()));

        // Configure mock behavior
        when(athleteRepository.findByAgeAndGender(age, gender)).thenReturn(expectedAthletes);

        // Call the service method
        List<Athlete> actualAthletes = athleteService.findAthletesByAgeAndGender(age, gender);

        // Assertions
        assertEquals(expectedAthletes, actualAthletes, "The returned athletes should match the expected ones");
        verify(athleteRepository).findByAgeAndGender(age, gender); // Verify that the repository method was called with correct parameters
    }
}
