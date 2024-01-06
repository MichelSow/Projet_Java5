package be.helb.misow.Service;

import be.helb.misow.client.DataAccessSponsor;
import be.helb.misow.Dto.SponsorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Classe de service pour gérer les opérations liées aux sponsors
@Service
public class SponsorService {

    // Référence à l'interface DataAccessSponsor pour l'accès aux données des sponsors
    private final DataAccessSponsor dataAccessSponsor;

    // Constructeur pour injecter la dépendance DataAccessSponsor
    @Autowired
    public SponsorService(DataAccessSponsor dataAccessSponsor) {
        this.dataAccessSponsor = dataAccessSponsor;
    }

    // Méthode pour obtenir tous les sponsors
    public List<SponsorDto> getAllSponsors() {
        // Récupère la liste de tous les sponsors via DataAccessSponsor
        return dataAccessSponsor.getAllSponsors();
    }

    // Méthode pour ajouter un sponsor
    public void addSponsor(SponsorDto sponsorDto) {
        // Ajoute un sponsor via DataAccessSponsor
        dataAccessSponsor.addSponsor(sponsorDto);
    }

    // Méthode pour supprimer un sponsor par son identifiant
    public void deleteSponsor(Long id) {
        // Supprime un sponsor par son ID via DataAccessSponsor
        dataAccessSponsor.deleteSponsor(id);
    }


}
