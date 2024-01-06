package be.helb.misow.Controller;

import be.helb.misow.Model.Result;
import be.helb.misow.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour la gestion des résultats sportifs
@RestController
public class ResultController {

    // Injection du service ResultService pour gérer les opérations sur les résultats
    @Autowired
    private ResultService resultService;

    // Méthode pour obtenir tous les résultats sportifs

    @GetMapping("/getAllResult")
    public List<Result> getAllResult() {
        // Appel à ResultService pour obtenir la liste des résultats
        return this.resultService.getAllResult();
    }

    // Méthode pour ajouter un nouveau résultat sportif

    @PostMapping("/addResult")
    public void addResult(@RequestBody Result result) {
        // Appel à ResultService pour ajouter le résultat fourni
        this.resultService.addResult(result);
    }

    // Méthode pour supprimer un résultat sportif par son identifiant

    @DeleteMapping("/deleteResult/{id}")
    public void deleteResult(@PathVariable Long id) {
        // Appel à ResultService pour supprimer le résultat avec l'ID spécifié
        this.resultService.deleteResultById(id);
    }


}
