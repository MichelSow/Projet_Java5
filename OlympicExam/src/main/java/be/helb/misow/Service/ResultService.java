package be.helb.misow.Service;

import be.helb.misow.Dao.AthleteRepository;
import be.helb.misow.Dao.ResultRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Dao.TeamRepository;
import be.helb.misow.Model.Result;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux résultats sportifs
@Service
public class ResultService {

    // Références aux repositories pour accéder aux données
    ResultRepository resultRepository;
    AthleteRepository athleteRepository;
    TeamRepository teamRepository;
    SportRepository sportRepository;

    // Constructeur pour injecter les dépendances aux repositories
    public ResultService(ResultRepository resultRepository, AthleteRepository athleteRepository,
                         TeamRepository teamRepository,
                         SportRepository sportRepository) {
        this.resultRepository = resultRepository;
        this.athleteRepository = athleteRepository;
        this.teamRepository = teamRepository;
        this.sportRepository = sportRepository;
    }

    // Méthode pour ajouter un résultat sportif à la base de données
    public void addResult(Result result) {
        // Sauvegarde du résultat dans la base de données
        this.resultRepository.save(result);
    }

    // Méthode pour supprimer un résultat sportif par son identifiant
    public void deleteResultById(Long id) {
        // Suppression du résultat de la base de données par son ID
        this.resultRepository.deleteById(id);
    }

    // Méthode pour récupérer la liste de tous les résultats sportifs
    public List<Result> getAllResult() {
        // Retourne la liste de tous les résultats sportifs
        return this.resultRepository.findAll();
    }


}
