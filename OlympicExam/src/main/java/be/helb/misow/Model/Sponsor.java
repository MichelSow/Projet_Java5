package be.helb.misow.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Classe représentant un sponsor dans le modèle de données
@Getter // Génère automatiquement les getters pour tous les champs
@Setter // Génère automatiquement les setters pour tous les champs
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
@Table(name = "sponsors") // Spécifie le nom de la table dans la base de données
public class Sponsor {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique du sponsor

    @Column(nullable = false) // Marque le champ comme non nullable dans la base de données
    private String name; // Nom du sponsor

    // Constructeur avec argument pour créer une instance de Sponsor
    public Sponsor(String name) {
        this.name = name;
    }


}
