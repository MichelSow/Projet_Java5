package be.helb.misow.Service;

import be.helb.misow.Dao.MedalRepository;
import be.helb.misow.Model.Athlete;
import be.helb.misow.Model.Medal;
import be.helb.misow.Model.Sport;
import be.helb.misow.Model.Team;
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
public class MedalServiceTest {

    @Mock
    private MedalRepository medalRepository; // Mock du repository Medal

    @InjectMocks
    private MedalService medalService; // Service Medal avec le mock injecté

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenAddMedal_thenMedalShouldBeSaved() {
        // Test pour ajouter une médaille
        Medal medal = new Medal("Gold", new Sport(), new Athlete(), new Team());
        medalService.addMedal(medal); // Ajout de la médaille
        verify(medalRepository).save(medal); // Vérification que la méthode save du repository a été appelée
    }

    @Test
    public void whenDeleteMedal_thenMedalShouldBeDeleted() {
        // Test pour supprimer une médaille
        Long medalId = 1L; // ID de la médaille à supprimer
        medalService.deleteMedalById(medalId); // Suppression de la médaille
        verify(medalRepository).deleteById(medalId); // Vérification que la méthode deleteById du repository a été appelée
    }

    @Test
    public void whenGetAllMedal_thenAllMedalsShouldBeReturned() {
        // Test pour obtenir toutes les médailles
        List<Medal> expectedMedals = Arrays.asList(new Medal("Gold", new Sport(), new Athlete(), new Team()));
        when(medalRepository.findAll()).thenReturn(expectedMedals); // Configuration du comportement du mock
        List<Medal> actualMedals = medalService.getAllMedal(); // Obtention des médailles
        assertEquals(expectedMedals, actualMedals); // Vérification que les médailles attendues et actuelles sont les mêmes
        verify(medalRepository).findAll(); // Vérification que la méthode findAll du repository a été appelée
    }
}
