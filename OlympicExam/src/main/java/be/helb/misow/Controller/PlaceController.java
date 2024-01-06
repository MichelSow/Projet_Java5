package be.helb.misow.Controller;

import be.helb.misow.Model.Place;
import be.helb.misow.Service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour gérer les lieux
@RestController
public class PlaceController {

    // Injection du service PlaceService pour gérer les opérations sur les lieux
    @Autowired
    private PlaceService placeService;

    // Méthode pour obtenir tous les lieux

    @GetMapping("/getAllPlace")
    public List<Place> getAllPlace() {
        // Appel à PlaceService pour obtenir la liste des lieux
        return this.placeService.getAllPlace();
    }

    // Méthode pour ajouter un nouveau lieu

    @PostMapping("/addPlace")
    public void addPlace(@RequestBody Place place) {
        // Appel à PlaceService pour ajouter le lieu fourni
        this.placeService.addPlace(place);
    }

    // Méthode pour supprimer un lieu par son identifiant

    @DeleteMapping("/deletePlace/{id}")
    public void deletePlace(@PathVariable Long id) {
        // Appel à PlaceService pour supprimer le lieu avec l'ID spécifié
        this.placeService.deletePlaceById(id);
    }


}
