package be.helb.misow.Controller;

import be.helb.misow.Model.Medal;
import be.helb.misow.Service.MedalService;
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

// Classe de test pour MedalController
@ExtendWith(MockitoExtension.class)
public class MedalControllerTest {

    @Mock
    private MedalService medalService; // Mock du service MedalService

    @InjectMocks
    private MedalController medalController; // Injection du mock dans MedalController

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllMedal_thenMedalsShouldBeReturned() {
        // Arrange (Préparation)
        List<Medal> expectedMedals = Arrays.asList(new Medal(), new Medal()); // Liste fictive de médailles
        when(medalService.getAllMedal()).thenReturn(expectedMedals); // Configuration du mock pour retourner la liste attendue

        // Act (Action)
        List<Medal> actualMedals = medalController.getAllMedal(); // Appel de la méthode du contrôleur

        // Assert (Vérification)
        assertNotNull(actualMedals); // Vérifie que la liste de médailles n'est pas nulle
        assertEquals(expectedMedals.size(), actualMedals.size()); // Vérifie que la taille de la liste est correcte
        verify(medalService).getAllMedal(); // Vérifie que la méthode getAllMedal du service a été appelée
    }

    @Test
    public void whenAddMedal_thenMedalShouldBeAdded() {
        // Arrange
        Medal newMedal = new Medal(); // Création d'une nouvelle médaille

        // Act
        medalController.addMedal(newMedal); // Appel de la méthode du contrôleur pour ajouter une médaille

        // Assert
        verify(medalService).addMedal(newMedal); // Vérifie que la méthode addMedal du service a été appelée avec newMedal
    }

    @Test
    public void whenDeleteMedal_thenMedalShouldBeDeleted() {
        // Arrange
        Long medalId = 1L; // Identifiant de la médaille à supprimer

        // Act
        medalController.deleteMedal(medalId); // Appel de la méthode du contrôleur pour supprimer une médaille

        // Assert
        verify(medalService).deleteMedalById(medalId); // Vérifie que la méthode deleteMedalById du service a été appelée avec medalId
    }
}
