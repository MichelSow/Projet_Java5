package be.helb.misow.Controller;

import be.helb.misow.Model.Team;
import be.helb.misow.Service.TeamService;
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

// Classe de test pour TeamController
@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamService; // Mock du service TeamService

    @InjectMocks
    private TeamController teamController; // Injection du mock dans TeamController

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllTeam_thenTeamsShouldBeReturned() {
        // Arrange (Préparation)
        // Création d'une liste attendue d'équipes
        List<Team> expectedTeams = Arrays.asList(new Team(), new Team());
        // Configuration du mock pour retourner la liste attendue
        when(teamService.getAllteam()).thenReturn(expectedTeams);

        // Act (Action)
        // Appel de la méthode du contrôleur pour obtenir toutes les équipes
        List<Team> actualTeams = teamController.getAllTeam();

        // Assert (Vérification)
        // Vérification que la liste d'équipes n'est pas nulle et a la bonne taille
        assertNotNull(actualTeams);
        assertEquals(expectedTeams.size(), actualTeams.size());
        // Vérification que le service a bien été appelé
        verify(teamService).getAllteam();
    }

    @Test
    public void whenAddTeam_thenTeamShouldBeAdded() {
        // Arrange
        // Création d'une nouvelle équipe à ajouter
        Team newTeam = new Team();

        // Act
        // Appel de la méthode du contrôleur pour ajouter une équipe
        teamController.addTeam(newTeam);

        // Assert
        // Vérification que la méthode addTeam du service a bien été appelée avec newTeam
        verify(teamService).addTeam(newTeam);
    }

    @Test
    public void whenDeleteTeam_thenTeamShouldBeDeleted() {
        // Arrange
        // Définition de l'identifiant de l'équipe à supprimer
        Long teamId = 1L;

        // Act
        // Appel de la méthode du contrôleur pour supprimer une équipe
        teamController.deleteTeam(teamId);

        // Assert
        // Vérification que la méthode deleteTeamById du service a bien été appelée avec teamId
        verify(teamService).deleteTeamById(teamId);
    }
}
