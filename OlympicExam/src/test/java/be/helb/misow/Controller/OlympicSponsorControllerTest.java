package be.helb.misow.Controller;

import be.helb.misow.client.DataAccessSponsor;
import be.helb.misow.Dto.SponsorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// Classe de test pour OlympicSponsorController
@ExtendWith(MockitoExtension.class)
public class OlympicSponsorControllerTest {

    @Mock
    private DataAccessSponsor dataAccessSponsor; // Mock de l'interface DataAccessSponsor

    @InjectMocks
    private OlympicSponsorController olympicSponsorController; // Injection du mock dans OlympicSponsorController

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllSponsors_thenSponsorsShouldBeReturned() {
        // Arrange (Préparation)
        // Création d'une liste attendue de SponsorDto
        List<SponsorDto> expectedSponsors = Arrays.asList(
                new SponsorDto(1L, "Sponsor1"),
                new SponsorDto(2L, "Sponsor2")
        );
        // Configuration du mock pour retourner la liste attendue
        when(dataAccessSponsor.getAllSponsors()).thenReturn(expectedSponsors);

        // Act (Action)
        // Appel de la méthode du contrôleur pour obtenir tous les sponsors
        ResponseEntity<List<SponsorDto>> response = olympicSponsorController.getAllSponsors();

        // Assert (Vérification)
        // Vérification que la réponse n'est pas nulle, a la bonne taille et contient les bons éléments
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Sponsor1", response.getBody().get(0).getName());
        // Vérification que le client Feign mocké a été appelé
        verify(dataAccessSponsor).getAllSponsors();
    }
}
