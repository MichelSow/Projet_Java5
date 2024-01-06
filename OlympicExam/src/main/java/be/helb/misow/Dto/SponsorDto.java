package be.helb.misow.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Classe DTO (Data Transfer Object) pour les sponsors
@Getter // Génère automatiquement les getters pour tous les champs
@Setter // Génère automatiquement les setters pour tous les champs
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les champs comme arguments
public class SponsorDto {
    private Long id; // Identifiant unique du sponsor
    private String name; // Nom du sponsor


}
