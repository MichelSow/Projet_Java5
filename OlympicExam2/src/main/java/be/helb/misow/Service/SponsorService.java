package be.helb.misow.Service;

import be.helb.misow.Dao.SponsorRepository;
import be.helb.misow.Model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Classe de service pour gérer les opérations sur les sponsors
@Service // Indique qu'il s'agit d'un service Spring
public class SponsorService {

    private final SponsorRepository sponsorRepository; // Référence au repository pour les opérations CRUD

    // Injection du SponsorRepository via le constructeur
    @Autowired
    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    // Récupère la liste de tous les sponsors
    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    // Ajoute un nouveau sponsor et le retourne
    public Sponsor addSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    // Trouve un sponsor par son ID et retourne un Optional de Sponsor
    public Optional<Sponsor> getSponsorById(Long id) {
        return sponsorRepository.findById(id);
    }

    // Supprime un sponsor par son ID
    public void deleteSponsorById(Long id) {
        sponsorRepository.deleteById(id);
    }


}
