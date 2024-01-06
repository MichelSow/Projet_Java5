package be.helb.misow.Service;

import be.helb.misow.Dao.CountryRepository;
import be.helb.misow.Model.Country;
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
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository; // Mock du repository Country

    @InjectMocks
    private CountryService countryService; // Service Country avec le mock injecté

    @BeforeEach
    public void setUp() {
        // Configuration initiale pour les tests, si nécessaire
    }

    @Test
    public void whenAddCountry_thenCountryShouldBeSaved() {
        // Test pour ajouter un pays
        Country country = new Country("France");
        countryService.addCountry(country); // Ajout du pays
        verify(countryRepository).save(country); // Vérification que la méthode save du repository a été appelée
    }

    @Test
    public void whenDeleteCountry_thenCountryShouldBeDeleted() {
        // Test pour supprimer un pays
        Long countryId = 1L; // ID du pays à supprimer
        countryService.deleteCountryById(countryId); // Suppression du pays
        verify(countryRepository).deleteById(countryId); // Vérification que la méthode deleteById du repository a été appelée
    }

    @Test
    public void whenGetAllCountry_thenAllCountriesShouldBeReturned() {
        // Test pour obtenir tous les pays
        List<Country> expectedCountries = Arrays.asList(new Country("France"), new Country("Germany"));
        when(countryRepository.findAll()).thenReturn(expectedCountries); // Configuration du comportement du mock
        List<Country> actualCountries = countryService.getAllCountry(); // Obtention des pays
        assertEquals(expectedCountries, actualCountries); // Vérification que les pays attendus et actuels sont les mêmes
        verify(countryRepository).findAll(); // Vérification que la méthode findAll du repository a été appelée
    }
}
