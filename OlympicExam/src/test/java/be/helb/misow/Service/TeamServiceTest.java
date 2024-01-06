package be.helb.misow.Service;

import be.helb.misow.Dao.TeamRepository;
import be.helb.misow.Model.Country;
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
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository; // Mock du repository TeamRepository

    @InjectMocks
    private TeamService teamService; // Injection du mock dans TeamService

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    // Test pour ajouter une équipe
    @Test
    public void whenAddTeam_thenTeamShouldBeSaved() {
        Team team = new Team("TeamName", new Sport(), new Country());
        teamService.addTeam(team); // Appel de la méthode à tester
        verify(teamRepository).save(team); // Vérification que la méthode save a été appelée
    }

    // Test pour supprimer une équipe par ID
    @Test
    public void whenDeleteTeamById_thenTeamShouldBeDeleted() {
        Long teamId = 1L;
        teamService.deleteTeamById(teamId); // Appel de la méthode à tester
        verify(teamRepository).deleteById(teamId); // Vérification que la méthode deleteById a été appelée
    }

    // Test pour obtenir toutes les équipes
    @Test
    public void whenGetAllTeam_thenAllTeamsShouldBeReturned() {
        List<Team> expectedTeams = Arrays.asList(new Team("Team1", new Sport(), new Country()),
                new Team("Team2", new Sport(), new Country()));
        when(teamRepository.findAll()).thenReturn(expectedTeams); // Comportement attendu du mock

        List<Team> actualTeams = teamService.getAllteam(); // Appel de la méthode à tester

        assertEquals(expectedTeams, actualTeams); // Vérification que les équipes retournées correspondent aux attentes
        verify(teamRepository).findAll(); // Vérification que la méthode findAll a été appelée
    }
}
