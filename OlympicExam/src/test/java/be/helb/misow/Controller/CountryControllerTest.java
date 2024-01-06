package be.helb.misow.Controller;

import be.helb.misow.Model.Country;
import be.helb.misow.Service.CountryService;
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

// Classe de test pour CountryController
@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    @Mock
    private CountryService countryService; // Création d'un mock pour CountryService

    @InjectMocks
    private CountryController countryController; // Injection du mock dans CountryController

    @BeforeEach
    public void setUp() {
        // Méthode exécutée avant chaque test pour la configuration initiale
    }

    @Test
    public void whenGetAllCountry_thenCountriesShouldBeReturned() {
        // Arrange (Préparation)
        // Création d'une liste de pays attendue
        List<Country> expectedCountries = Arrays.asList(new Country("Belgium"), new Country("France"));
        // Configuration du mock pour retourner la liste attendue
        when(countryService.getAllCountry()).thenReturn(expectedCountries);

        // Act (Action)
        // Appel de la méthode du contrôleur à tester
        List<Country> countries = countryController.getAllCountry();

        // Assert (Vérification)
        // Vérification que la liste de pays n'est pas nulle, a la bonne taille et contient les bons éléments
        assertNotNull(countries);
        assertEquals(2, countries.size());
        assertEquals("Belgium", countries.get(0).getName());
        // Vérification que le service a bien été appelé
        verify(countryService).getAllCountry();
    }

    @Test
    public void whenAddCountry_thenCountryShouldBeAdded() {
        // Arrange
        // Création d'un nouveau pays à ajouter
        Country newCountry = new Country("Germany");

        // Act
        // Appel de la méthode du contrôleur pour ajouter un pays
        countryController.addCountry(newCountry);

        // Assert
        // Vérification que la méthode addCountry du service a bien été appelée avec le nouveau pays
        verify(countryService).addCountry(newCountry);
    }

    @Test
    public void whenDeleteCountry_thenCountryShouldBeDeleted() {
        // Arrange
        // Définition de l'identifiant du pays à supprimer
        Long countryId = 1L;

        // Act
        // Appel de la méthode du contrôleur pour supprimer un pays
        countryController.deleteCountry(countryId);

        // Assert
        // Vérification que la méthode deleteCountryById du service a bien été appelée avec l'identifiant du pays
        verify(countryService).deleteCountryById(countryId);
    }
}
