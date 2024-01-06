package be.helb.misow.Service;

import be.helb.misow.Dao.SportRepository;
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
public class SportServiceTest {

    @Mock
    private SportRepository sportRepository; // Mock du repository SportRepository

    @InjectMocks
    private SportService sportService; // Injection du mock dans SportService

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    // Test pour ajouter un sport
    @Test
    public void whenAddSport_thenSportShouldBeSaved() {
        Sport sport = new Sport("Basketball", "Ball Game");
        sportService.addSport(sport); // Appel de la méthode à tester
        verify(sportRepository).save(sport); // Vérification que la méthode save a été appelée
    }

    // Test pour supprimer un sport par ID
    @Test
    public void whenDeleteSportById_thenSportShouldBeDeleted() {
        Long sportId = 1L;
        sportService.deleteSportById(sportId); // Appel de la méthode à tester
        verify(sportRepository).deleteById(sportId); // Vérification que la méthode deleteById a été appelée
    }

    // Test pour obtenir tous les sports
    @Test
    public void whenGetAllSport_thenAllSportsShouldBeReturned() {
        List<Sport> expectedSports = Arrays.asList(new Sport("Football", "Ball Game"), new Sport("Tennis", "Racket Game"));
        when(sportRepository.findAll()).thenReturn(expectedSports); // Comportement attendu du mock

        List<Sport> actualSports = sportService.getAllSport(); // Appel de la méthode à tester

        assertEquals(expectedSports, actualSports); // Vérification que les sports retournés correspondent aux attentes
        verify(sportRepository).findAll(); // Vérification que la méthode findAll a été appelée
    }
}
