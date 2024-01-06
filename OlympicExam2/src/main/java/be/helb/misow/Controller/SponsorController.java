package be.helb.misow.Controller;

import be.helb.misow.Model.Sponsor;
import be.helb.misow.Service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Contrôleur REST pour la gestion des sponsors
@RestController
@RequestMapping("/sponsors")
public class SponsorController {

    private final SponsorService sponsorService;

    // Injection du service SponsorService
    @Autowired
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    // Méthode pour récupérer tous les sponsors
    @GetMapping
    public ResponseEntity<List<Sponsor>> getAllSponsors() {
        List<Sponsor> sponsors = sponsorService.getAllSponsors();
        return ResponseEntity.ok(sponsors); // Retourne la liste des sponsors avec le statut 200 OK
    }

    // Méthode pour ajouter un nouveau sponsor
    @PostMapping
    public ResponseEntity<Sponsor> addSponsor(@RequestBody Sponsor sponsor) {
        Sponsor newSponsor = sponsorService.addSponsor(sponsor);
        return new ResponseEntity<>(newSponsor, HttpStatus.CREATED); // Retourne le sponsor ajouté avec le statut 201 Créé
    }

    // Méthode pour récupérer un sponsor par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id)
                .map(ResponseEntity::ok) // Si trouvé, retourne le sponsor avec le statut 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Sinon, retourne le statut 404 Non Trouvé
    }

    // Méthode pour supprimer un sponsor par son identifiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteSponsorById(id);
        return ResponseEntity.ok().build(); // Retourne le statut 200 OK après suppression
    }


}
