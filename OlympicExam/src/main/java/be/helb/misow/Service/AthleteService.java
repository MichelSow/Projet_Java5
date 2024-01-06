package be.helb.misow.Service;

import be.helb.misow.Dao.AthleteRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Model.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux athlètes
@Service
public class AthleteService {

    // Référence au repository Athlete pour l'accès aux données des athlètes
    AthleteRepository athleteRepository;

    // Référence au repository Sport (peut être utilisé pour des opérations liées aux sports)
    SportRepository sportRepository;

    // Constructeur pour injecter les dépendances AthleteRepository et SportRepository
    public AthleteService(AthleteRepository athleteRepository, SportRepository sportRepository) {
        this.athleteRepository = athleteRepository;
        this.sportRepository = sportRepository;
    }

    // Méthode pour ajouter un athlète à la base de données
    public void addAthlete(Athlete athlete) {
        // Sauvegarde de l'athlète dans la base de données
        this.athleteRepository.save(athlete);
    }

    // Méthode pour supprimer un athlète par son identifiant
    public void deleteAthleteById(Long id) {
        // Suppression de l'athlète de la base de données par son ID
        this.athleteRepository.deleteById(id);
    }

    // Méthode pour récupérer la liste de tous les athlètes
    public List<Athlete> getAllAthlete() {
        // Retourne la liste de tous les athlètes
        return this.athleteRepository.findAll();
    }

    // Methode pour retourner les athletes par age ou genre 
    public List<Athlete> findAthletesByAgeAndGender(int age, char gender) {
        return athleteRepository.findByAgeAndGender(age, gender);
    }


}
