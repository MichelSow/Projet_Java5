package be.helb.misow.Service;

import be.helb.misow.Dao.AthleteRepository;
import be.helb.misow.Dao.MedalRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Dao.TeamRepository;
import be.helb.misow.Model.Medal;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux médailles
@Service
public class MedalService {

    // Références aux repositories pour accéder aux données
    MedalRepository medalRepository;
    SportRepository sportRepository;
    AthleteRepository athleteRepository;
    TeamRepository teamRepository;

    // Constructeur pour injecter les dépendances aux repositories
    public MedalService(MedalRepository medalRepository, AthleteRepository athleteRepository, TeamRepository teamRepository, SportRepository sportRepository) {
        this.medalRepository = medalRepository;
        this.athleteRepository = athleteRepository;
        this.teamRepository = teamRepository;
        this.sportRepository = sportRepository;
    }

    // Méthode pour ajouter une médaille à la base de données
    public void addMedal(Medal medal) {
        // Sauvegarde de la médaille dans la base de données
        this.medalRepository.save(medal);
    }

    // Méthode pour supprimer une médaille par son identifiant
    public void deleteMedalById(Long id) {
        // Suppression de la médaille de la base de données par son ID
        this.medalRepository.deleteById(id);
    }

    // Méthode pour récupérer la liste de toutes les médailles
    public List<Medal> getAllMedal() {
        // Retourne la liste de toutes les médailles
        return this.medalRepository.findAll();
    }

   
}
