package be.helb.misow.client;

import be.helb.misow.Dto.SponsorDto; // Import du DTO Sponsor, assurez-vous que cette classe est correctement définie dans votre projet
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * Interface représentant les endpoints de l'API pour accéder aux données des sponsors.
 * Cette interface utilise Feign pour déclarer des méthodes qui correspondent aux actions de l'API.
 */
public interface DataAccessSponsor {

    /**
     * Récupère tous les sponsors.
     * Envoie une requête GET à l'endpoint /sponsors pour obtenir une liste de tous les sponsors.
     *
     * @return Une liste de tous les sponsors sous forme de SponsorDto.
     */
    @RequestLine("GET /sponsors")
    @Headers("Content-Type: application/json")
    List<SponsorDto> getAllSponsors();

    /**
     * Ajoute un nouveau sponsor.
     * Envoie une requête POST à l'endpoint /sponsors pour ajouter un nouveau sponsor.
     *
     * @param sponsorDto Le DTO représentant le sponsor à ajouter.
     */
    @RequestLine("POST /sponsors")
    @Headers("Content-Type: application/json")
    void addSponsor(SponsorDto sponsorDto);

    /**
     * Supprime un sponsor par son identifiant.
     * Envoie une requête DELETE à l'endpoint /sponsors/{id} pour supprimer le sponsor spécifié.
     *
     * @param id L'identifiant du sponsor à supprimer.
     */
    @RequestLine("DELETE /sponsors/{id}")
    @Headers("Content-Type: application/json")
    void deleteSponsor(@Param("id") Long id);
}
