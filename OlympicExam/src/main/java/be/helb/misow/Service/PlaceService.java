package be.helb.misow.Service;

import be.helb.misow.Dao.PlaceRepository;
import be.helb.misow.Model.Place;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux lieux
@Service
public class PlaceService {

    // Référence au repository Place pour l'accès aux données des lieux
    PlaceRepository placeRepository;

    // Constructeur pour injecter la dépendance PlaceRepository
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // Méthode pour ajouter un lieu à la base de données
    public void addPlace(Place place) {
        // Sauvegarde du lieu dans la base de données
        placeRepository.save(place);
    }

    // Méthode pour supprimer un lieu par son identifiant
    public void deletePlaceById(Long id) {
        // Suppression du lieu de la base de données par son ID
        this.placeRepository.deleteById(id);
    }

    // Méthode pour récupérer la liste de tous les lieux
    public List<Place> getAllPlace() {
        // Retourne la liste de tous les lieux
        return this.placeRepository.findAll();
    }

    // Méthode pour mettre à jour le nom d'un lieu
    public void updatePlaceName(Long id, String newName) {
        // Trouver le lieu par son ID
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found with id : " + id));

        // Mettre à jour le nom du lieu
        place.setName(newName);

        // Sauvegarder le lieu mis à jour dans la base de données
        placeRepository.save(place);
    }


}
