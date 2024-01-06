package be.helb.misow.Controller;

import be.helb.misow.Dto.SponsorDto;
import be.helb.misow.client.DataAccessSponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Contrôleur REST dédié à la gestion des sponsors dans le contexte olympique
@RestController
@RequestMapping("/olympic2")
public class OlympicSponsorController {

    // Client Feign pour accéder aux données des sponsors
    private final DataAccessSponsor dataAccessSponsor;

    // Injection du client Feign DataAccessSponsor
    @Autowired
    public OlympicSponsorController(DataAccessSponsor dataAccessSponsor) {
        this.dataAccessSponsor = dataAccessSponsor;
    }

    // Endpoint pour obtenir tous les sponsors

    @GetMapping("/getAllSponsors")
    public ResponseEntity<List<SponsorDto>> getAllSponsors() {
        // Récupération de la liste des sponsors via le client Feign
        List<SponsorDto> sponsors = dataAccessSponsor.getAllSponsors();
        // Retourne la liste des sponsors avec un statut HTTP OK
        return ResponseEntity.ok(sponsors);
    }


}
