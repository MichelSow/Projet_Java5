package be.helb.misow.Controller;

import be.helb.misow.Model.Medal;
import be.helb.misow.Service.MedalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour la gestion des médailles
@RestController
public class MedalController {

    // Injection du service de gestion des médailles
    @Autowired
    private MedalService medalService;

    // Endpoint pour récupérer toutes les médailles
    @GetMapping("/getAllMedal")
    public List<Medal> getAllMedal() {
        // Appelle la méthode getAllMedal du service pour obtenir la liste des médailles
        return this.medalService.getAllMedal();
    }

    // Endpoint pour ajouter une nouvelle médaille
    @PostMapping("/addMedal")
    public void addMedal(@RequestBody Medal medal) {
        // Appelle la méthode addMedal du service pour ajouter la médaille fournie
        this.medalService.addMedal(medal);
    }

    // Endpoint pour supprimer une médaille spécifiée par son identifiant
    @DeleteMapping("/deleteMedal/{id}")
    public void deleteMedal(@PathVariable Long id) {
        // Appelle la méthode deleteMedalById du service pour supprimer la médaille avec l'identifiant spécifié
        this.medalService.deleteMedalById(id);
    }


}
