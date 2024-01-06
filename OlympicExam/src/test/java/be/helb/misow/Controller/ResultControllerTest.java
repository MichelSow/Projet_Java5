package be.helb.misow.Controller;

import be.helb.misow.Model.Result;
import be.helb.misow.Service.ResultService;
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

// Classe de test pour ResultController
@ExtendWith(MockitoExtension.class)
public class ResultControllerTest {

    @Mock
    private ResultService resultService; // Mock du service ResultService

    @InjectMocks
    private ResultController resultController; // Injection du mock dans ResultController

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllResult_thenResultsShouldBeReturned() {
        // Arrange (Préparation)
        // Création d'une liste attendue de résultats
        List<Result> expectedResults = Arrays.asList(new Result(), new Result());
        // Configuration du mock pour retourner la liste attendue
        when(resultService.getAllResult()).thenReturn(expectedResults);

        // Act (Action)
        // Appel de la méthode du contrôleur pour obtenir tous les résultats
        List<Result> actualResults = resultController.getAllResult();

        // Assert (Vérification)
        // Vérification que la liste de résultats n'est pas nulle et a la bonne taille
        assertNotNull(actualResults);
        assertEquals(expectedResults.size(), actualResults.size());
        // Vérification que le service a bien été appelé
        verify(resultService).getAllResult();
    }

    @Test
    public void whenAddResult_thenResultShouldBeAdded() {
        // Arrange
        // Création d'un nouveau résultat à ajouter
        Result newResult = new Result();

        // Act
        // Appel de la méthode du contrôleur pour ajouter un résultat
        resultController.addResult(newResult);

        // Assert
        // Vérification que la méthode addResult du service a bien été appelée avec newResult
        verify(resultService).addResult(newResult);
    }

    @Test
    public void whenDeleteResult_thenResultShouldBeDeleted() {
        // Arrange
        // Définition de l'identifiant du résultat à supprimer
        Long resultId = 1L;

        // Act
        // Appel de la méthode du contrôleur pour supprimer un résultat
        resultController.deleteResult(resultId);

        // Assert
        // Vérification que la méthode deleteResultById du service a bien été appelée avec resultId
        verify(resultService).deleteResultById(resultId);
    }
}
