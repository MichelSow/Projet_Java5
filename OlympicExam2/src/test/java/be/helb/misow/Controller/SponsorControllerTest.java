package be.helb.misow.Controller;

import be.helb.misow.Controller.SponsorController;
import be.helb.misow.Model.Sponsor;
import be.helb.misow.Service.SponsorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SponsorControllerTest {

    @Mock
    private SponsorService sponsorService;

    @InjectMocks
    private SponsorController sponsorController;

    @BeforeEach
    void setUp() {
        // Initialisation avant chaque test
    }


    @Test
    void testGetAllSponsors() {
        // Préparation des données pour le test
        Sponsor sponsor1 = new Sponsor("Sponsor 1");
        Sponsor sponsor2 = new Sponsor("Sponsor 2");
        List<Sponsor> expectedSponsors = Arrays.asList(sponsor1, sponsor2);
        when(sponsorService.getAllSponsors()).thenReturn(expectedSponsors);

        // Exécution de la méthode à tester
        ResponseEntity<List<Sponsor>> response = sponsorController.getAllSponsors();

        // Vérification des résultats
        assertEquals(expectedSponsors, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddSponsor() {
        // Préparation des données pour le test
        Sponsor newSponsor = new Sponsor("Nouveau Sponsor");
        when(sponsorService.addSponsor(any(Sponsor.class))).thenReturn(newSponsor);

        // Exécution de la méthode à tester
        ResponseEntity<Sponsor> response = sponsorController.addSponsor(newSponsor);

        // Vérification des résultats
        assertEquals(newSponsor, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetSponsorById() {
        // Préparation des données pour le test
        Long id = 1L;
        Sponsor sponsor = new Sponsor("Sponsor Existant");
        when(sponsorService.getSponsorById(id)).thenReturn(Optional.of(sponsor));

        // Exécution de la méthode à tester
        ResponseEntity<Sponsor> response = sponsorController.getSponsorById(id);

        // Vérification des résultats
        assertEquals(sponsor, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteSponsor() {
        // Préparation des données pour le test
        Long id = 1L;
        doNothing().when(sponsorService).deleteSponsorById(id);

        // Exécution de la méthode à tester
        ResponseEntity<Void> response = sponsorController.deleteSponsor(id);

        // Vérification des résultats
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(sponsorService, times(1)).deleteSponsorById(id);
    }


}
