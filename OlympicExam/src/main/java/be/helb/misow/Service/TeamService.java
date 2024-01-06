package be.helb.misow.Service;

import be.helb.misow.Dao.CountryRepository;
import be.helb.misow.Dao.SportRepository;
import be.helb.misow.Dao.TeamRepository;
import be.helb.misow.Model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux équipes
@Service
public class TeamService {

    // Références aux repositories pour accéder aux données
    TeamRepository teamRepository;
    SportRepository sportRepository;
    CountryRepository countryRepository;

    // Constructeur pour injecter les dépendances aux repositories
    public TeamService(TeamRepository teamRepository, SportRepository sportRepository,
                       CountryRepository countryRepository) {
        this.teamRepository = teamRepository;
        this.sportRepository = sportRepository;
        this.countryRepository = countryRepository;
    }

    // Méthode pour ajouter une équipe à la base de données
    public void addTeam(Team team) {
        // Sauvegarde de l'équipe dans la base de données
        this.teamRepository.save(team);
    }

    // Méthode pour supprimer une équipe par son identifiant
    public void deleteTeamById(Long id) {
        // Suppression de l'équipe de la base de données par son ID
        this.teamRepository.deleteById(id);
    }

    // Méthode pour récupérer la liste de toutes les équipes
    public List<Team> getAllteam() {
        // Retourne la liste de toutes les équipes
        return this.teamRepository.findAll();
    }


}
