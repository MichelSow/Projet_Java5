package be.helb.misow.Service;

import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Model.Sport;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux sports
@Service
public class SportService {

    // Référence au repository Sport pour l'accès aux données des sports
    SportRepository sportRepository;

    // Constructeur pour injecter la dépendance SportRepository
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    // Méthode pour ajouter un sport à la base de données
    public void addSport(Sport sport) {
        // Sauvegarde du sport dans la base de données
        this.sportRepository.save(sport);
    }

    // Méthode pour supprimer un sport par son identifiant
    public void deleteSportById(Long id) {
        // Suppression du sport de la base de données par son ID
        this.sportRepository.deleteById(id);
    }

    // méthode pour supprimer un sport par son nom
    public void deleteSportByName(String name) {

        Sport sport = sportRepository.findByName(name);
        this.sportRepository.delete(sport);
    }

    // Méthode pour récupérer la liste de tous les sports
    public List<Sport> getAllSport() {
        // Retourne la liste de tous les sports
        return this.sportRepository.findAll();
    }


}
