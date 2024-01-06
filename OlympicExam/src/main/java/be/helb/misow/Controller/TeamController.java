package be.helb.misow.Controller;

import be.helb.misow.Model.Team;
import be.helb.misow.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour la gestion des équipes
@RestController
public class TeamController {

    // Injection du service TeamService pour gérer les opérations sur les équipes
    @Autowired
    private TeamService teamService;

    // Méthode pour obtenir toutes les équipes

    @GetMapping("/getAllTeam")
    public List<Team> getAllTeam() {
        // Appel à TeamService pour obtenir la liste des équipes
        return this.teamService.getAllteam();
    }

    // Méthode pour ajouter une nouvelle équipe

    @PostMapping("/addTeam")
    public void addTeam(@RequestBody Team team) {
        // Appel à TeamService pour ajouter l'équipe fournie
        this.teamService.addTeam(team);
    }

    // Méthode pour supprimer une équipe par son identifiant
    @DeleteMapping("/deleteTeam/{id}")
    public void deleteTeam(@PathVariable Long id) {
        // Appel à TeamService pour supprimer l'équipe avec l'ID spécifié
        this.teamService.deleteTeamById(id);
    }


}
