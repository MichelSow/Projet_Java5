package be.helb.misow.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Classe représentant un sport dans le modèle de données
@Getter // Génère automatiquement les getters pour tous les champs
@Setter // Génère automatiquement les setters pour tous les champs
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Sport {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique du sport

    private String name; // Nom du sport
    private String category; // Catégorie du sport (par exemple, sport individuel, sport d'équipe)

    // Constructeur avec arguments pour créer une instance de Sport
    public Sport(String name, String category) {
        this.name = name;
        this.category = category;
    }


}
