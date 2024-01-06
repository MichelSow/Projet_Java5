package be.helb.misow.Controller;

import be.helb.misow.Model.Sport;
import be.helb.misow.Service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour la gestion des sports
@RestController
public class SportController {

    // Injection du service SportService pour gérer les opérations sur les sports
    @Autowired
    private SportService sportService;

    // Méthode pour obtenir tous les sports

    @GetMapping("/getAllSport")
    public List<Sport> getAllSport() {
        // Appel à SportService pour obtenir la liste des sports
        return this.sportService.getAllSport();
    }

    // Méthode pour ajouter un nouveau sport

    @PostMapping("/addSport")
    public void addSport(@RequestBody Sport sport) {
        // Appel à SportService pour ajouter le sport fourni
        this.sportService.addSport(sport);
    }

    // Méthode pour supprimer un sport par son identifiant

    @DeleteMapping("/deleteSport/{id}")
    public void deleteSport(@PathVariable Long id) {
        // Appel à SportService pour supprimer le sport avec l'ID spécifié
        this.sportService.deleteSportById(id);
    }

    // Méthode pour supprimer un sport par son identifiant par son nom
    @DeleteMapping("/deleteSportByName/{name}")
    public void deleteSportByName(@PathVariable String name) {
        // Appel à SportService pour supprimer le sport avec l'ID spécifié
        this.sportService.deleteSportByName(name);
    }


}
