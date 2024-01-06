package be.helb.misow.Service;

import be.helb.misow.client.DataAccessSponsor;
import be.helb.misow.Dto.SponsorDto;
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
public class SponsorServiceTest {

    @Mock
    private DataAccessSponsor dataAccessSponsor; // Mock de l'interface DataAccessSponsor

    @InjectMocks
    private SponsorService sponsorService; // Injection du mock dans le service

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    // Test pour récupérer tous les sponsors
    @Test
    public void whenGetAllSponsors_thenSponsorsShouldBeReturned() {
        List<SponsorDto> expectedSponsors = Arrays.asList(new SponsorDto(1L, "Sponsor1"), new SponsorDto(2L, "Sponsor2"));
        when(dataAccessSponsor.getAllSponsors()).thenReturn(expectedSponsors); // Comportement attendu du mock

        List<SponsorDto> actualSponsors = sponsorService.getAllSponsors(); // Appel de la méthode à tester

        assertNotNull(actualSponsors); // Vérification que la liste n'est pas nulle
        assertEquals(expectedSponsors.size(), actualSponsors.size()); // Vérification de la taille de la liste
        verify(dataAccessSponsor).getAllSponsors(); // Vérification que la méthode getAllSponsors a été appelée
    }

    // Test pour ajouter un sponsor
    @Test
    public void whenAddSponsor_thenSponsorShouldBeAdded() {
        SponsorDto newSponsorDto = new SponsorDto(3L, "New Sponsor");

        sponsorService.addSponsor(newSponsorDto); // Appel de la méthode à tester

        verify(dataAccessSponsor).addSponsor(newSponsorDto); // Vérification que la méthode addSponsor a été appelée avec newSponsorDto
    }

    // Test pour supprimer un sponsor
    @Test
    public void whenDeleteSponsor_thenSponsorShouldBeDeleted() {
        Long sponsorId = 1L; // ID du sponsor à supprimer

        sponsorService.deleteSponsor(sponsorId); // Appel de la méthode à tester

        verify(dataAccessSponsor).deleteSponsor(sponsorId); // Vérification que la méthode deleteSponsor a été appelée avec sponsorId
    }
}
