package be.helb.misow.Controller;

import be.helb.misow.Model.Country;
import be.helb.misow.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour la gestion des pays
@RestController
public class CountryController {

    // Injection automatique du service de gestion des pays
    @Autowired
    private CountryService countryService;

    // Méthode pour obtenir tous les pays

    @GetMapping("/getAllCountry")
    public List<Country> getAllCountry() {
        // Appel au service CountryService pour récupérer la liste des pays
        return this.countryService.getAllCountry();
    }

    // Méthode pour ajouter un nouveau pays

    @PostMapping("/addCountry")
    public void addCountry(@RequestBody Country country) {
        // Appel au service CountryService pour ajouter le pays
        this.countryService.addCountry(country);
    }

    // Méthode pour supprimer un pays par son identifiant

    @DeleteMapping("/deleteCountry/{id}")
    public void deleteCountry(@PathVariable Long id) {
        // Appel au service CountryService pour supprimer le pays avec l'ID spécifié
        this.countryService.deleteCountryById(id);
    }


}
