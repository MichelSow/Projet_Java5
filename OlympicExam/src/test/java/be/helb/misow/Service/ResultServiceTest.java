package be.helb.misow.Service;

import be.helb.misow.Dao.ResultRepository;
import be.helb.misow.Model.Athlete;
import be.helb.misow.Model.Result;
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
public class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository; // Mock du repository Result

    @InjectMocks
    private ResultService resultService; // Service Result avec le mock injecté

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    // Test pour ajouter un résultat
    @Test
    public void whenAddResult_thenResultShouldBeSaved() {
        Result result = new Result(new Athlete(), new Team(), new Sport(), 1, 9.5);
        resultService.addResult(result); // Appel de la méthode pour ajouter un résultat
        verify(resultRepository).save(result); // Vérification que la méthode save du repository a été appelée
    }

    // Test pour supprimer un résultat
    @Test
    public void whenDeleteResult_thenResultShouldBeDeleted() {
        Long resultId = 1L; // ID du résultat à supprimer
        resultService.deleteResultById(resultId); // Appel de la méthode pour supprimer un résultat
        verify(resultRepository).deleteById(resultId); // Vérification que la méthode deleteById du repository a été appelée
    }

    // Test pour obtenir tous les résultats
    @Test
    public void whenGetAllResult_thenAllResultsShouldBeReturned() {
        List<Result> expectedResults = Arrays.asList(new Result(new Athlete(), new Team(), new Sport(), 1, 9.5),
                new Result(new Athlete(), new Team(), new Sport(), 2, 8.5));
        when(resultRepository.findAll()).thenReturn(expectedResults); // Configuration du comportement du mock
        List<Result> actualResults = resultService.getAllResult(); // Obtention des résultats
        assertEquals(expectedResults, actualResults); // Vérification que les résultats attendus et actuels sont les mêmes
        verify(resultRepository).findAll(); // Vérification que la méthode findAll du repository a été appelée
    }
}
