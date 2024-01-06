package be.helb.misow.Service;

import be.helb.misow.Dao.CountryRepository;
import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux pays
@Service
public class CountryService {

    // Référence au repository Country pour l'accès aux données des pays
    private final CountryRepository countryRepository;
    

    // Constructeur pour injecter la dépendance CountryRepository
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Méthode pour ajouter un pays à la base de données
    public void addCountry(Country country) {
        // Sauvegarde du pays dans la base de données
        this.countryRepository.save(country);
    }

    // Méthode pour supprimer un pays par son identifiant
    public void deleteCountryById(Long id) {
        // Suppression du pays de la base de données par son ID
        this.countryRepository.deleteById(id);
    }


    // Méthode pour récupérer la liste de tous les pays
    public List<Country> getAllCountry() {
        // Retourne la liste de tous les pays
        return this.countryRepository.findAll();
    }


}
