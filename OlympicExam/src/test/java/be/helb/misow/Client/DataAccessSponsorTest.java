package be.helb.misow.Client;

import be.helb.misow.Dto.SponsorDto;
import be.helb.misow.client.DataAccessSponsor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// Classe de test pour DataAccessSponsor
@ExtendWith(MockitoExtension.class)
public class DataAccessSponsorTest {

    @Mock
    private DataAccessSponsor dataAccessSponsor; // Mock de DataAccessSponsor

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenGetAllSponsors_thenClientShouldReturnList() {
        // Arrange (Préparation)
        SponsorDto mockSponsor = new SponsorDto(1L, "asus"); // Création d'un sponsor fictif pour le test
        List<SponsorDto> mockResponse = List.of(mockSponsor); // Liste fictive de sponsors
        when(dataAccessSponsor.getAllSponsors()).thenReturn(mockResponse); // Configuration du comportement du mock

        // Act (Action)
        List<SponsorDto> response = dataAccessSponsor.getAllSponsors(); // Appel de la méthode à tester

        // Assert (Vérification)
        assertNotNull(response); // Vérifier que la réponse n'est pas nulle
        assertFalse(response.isEmpty()); // Vérifier que la liste n'est pas vide
        assertEquals("asus", response.get(0).getName()); // Vérifier le nom du sponsor dans la réponse

        // Vérifier l'interaction avec le client Feign mocké
        verify(dataAccessSponsor).getAllSponsors();
    }

    @Test
    public void whenAddSponsor_thenClientShouldPostData() {
        // Arrange
        SponsorDto newSponsor = new SponsorDto(1L, "google"); // Création d'un nouveau sponsor fictif
        doNothing().when(dataAccessSponsor).addSponsor(any(SponsorDto.class)); // Configuration du comportement du mock

        // Act
        dataAccessSponsor.addSponsor(newSponsor); // Appel de la méthode à tester

        // Assert
        verify(dataAccessSponsor).addSponsor(newSponsor); // Vérifier que la méthode addSponsor a été appelée avec newSponsor
    }

    @Test
    public void whenDeleteSponsor_thenClientShouldDeleteData() {
        // Arrange
        Long sponsorId = 1L; // Identifiant du sponsor à supprimer
        doNothing().when(dataAccessSponsor).deleteSponsor(sponsorId); // Configuration du comportement du mock

        // Act
        dataAccessSponsor.deleteSponsor(sponsorId); // Appel de la méthode à tester

        // Assert
        verify(dataAccessSponsor).deleteSponsor(sponsorId); // Vérifier que la méthode deleteSponsor a été appelée avec l'identifiant sponsorId
    }
}
