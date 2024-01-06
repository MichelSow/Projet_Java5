package be.helb.misow.Controller;

import be.helb.misow.Model.Athlete;
import be.helb.misow.Service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Le contrôleur REST pour gérer les opérations liées aux athlètes
@RestController
public class AthleteController {

    // Injection de la dépendance AthleteService
    @Autowired
    private AthleteService athleteService;

    // Endpoint pour récupérer tous les athlètes
    @GetMapping("/getAllAthlete")
    public List<Athlete> getAllAthlete() {
        // Appel à AthleteService pour obtenir la liste des athlètes
        return this.athleteService.getAllAthlete();
    }

    // Endpoint pour trouver un athlete selon les preference de l'utilisant
    // Incluant différents parametres ( age et genre )
    // Exemple http://[host]/findAthletes?age=25&gender=F
    @GetMapping("/findAthletes")
    public List<Athlete> findAthletesByAgeAndGender(@RequestParam int age, @RequestParam char gender) {
        return athleteService.findAthletesByAgeAndGender(age, gender);
    }

    // Endpoint pour ajouter un nouvel athlète
    @PostMapping("/addAthlete")
    public void addAthlete(@RequestBody Athlete athlete) {
        // Appel à AthleteService pour ajouter l'athlète passé en paramètre
        this.athleteService.addAthlete(athlete);
    }

    // Endpoint pour supprimer un athlète par son ID
    @DeleteMapping("/deleteAthlete/{id}")
    public void deleteAthlete(@PathVariable Long id) {
        // Appel à AthleteService pour supprimer l'athlète avec l'ID spécifié
        this.athleteService.deleteAthleteById(id);
    }


}
