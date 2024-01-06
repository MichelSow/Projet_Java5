package be.helb.misow.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// Classe représentant une équipe dans le modèle de données
@Getter // Génère automatiquement les getters pour tous les champs
@Setter // Génère automatiquement les setters pour tous les champs
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Team {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique de l'équipe
    private String name; // Nom de l'équipe

    // Relation ManyToOne avec l'entité Sport
    @ManyToOne
    @JoinColumn(name = "sport_id") // Clé étrangère pour Sport
    private Sport sport; // Sport pratiqué par l'équipe

    // Relation ManyToOne avec l'entité Country
    @ManyToOne
    @JoinColumn(name = "country_id") // Clé étrangère pour Country
    @JsonIgnore
    private Country country; // Pays représenté par l'équipe

    // Relation ManyToMany avec l'entité Athlete
    @ManyToMany(mappedBy = "teams") // 'teams' est le nom du champ dans Athlete faisant référence à Team
    @JsonIgnore
    private List<Athlete> athletes; // Athlètes faisant partie de l'équipe

    // Relation OneToMany avec l'entité Result
    @OneToMany(mappedBy = "team") // 'team' est le nom du champ dans Result faisant référence à Team
    @JsonIgnore
    private List<Result> results; // Résultats obtenus par l'équipe

    // Constructeur avec arguments pour créer une instance de Team
    public Team(String name, Sport sport, Country country) {
        this.name = name;
        this.sport = sport;
        this.country = country;
    }


}
