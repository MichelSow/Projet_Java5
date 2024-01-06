package be.helb.misow.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// Classe représentant un athlète dans le modèle de données
@Getter
@Setter
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Athlete {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique de l'athlète
    private String name; // Nom de l'athlète
    private char gender; // Genre de l'athlète
    private int age; // Âge de l'athlète
    private String nationality; // Nationalité de l'athlète

    // Relation ManyToOne avec l'entité Country
    @ManyToOne
    @JoinColumn(name = "country_id") // Clé étrangère pour Country
    private Country country; // Pays représenté par l'athlète

    // Relation ManyToOne avec l'entité Sport
    @ManyToOne
    @JoinColumn(name = "sport_id") // Clé étrangère pour Sport
    private Sport sport; // Sport pratiqué par l'athlète

    // Relation ManyToMany avec l'entité Team
    @ManyToMany
    @JoinTable(
            name = "athlete_team", // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "athlete_id"), // Clé étrangère pour Athlete
            inverseJoinColumns = @JoinColumn(name = "team_id") // Clé étrangère pour Team
    )
    private List<Team> teams; // Équipes auxquelles l'athlète appartient

    // Constructeur avec arguments pour créer une instance d'Athlete
    public Athlete(String name, char gender, int age, String nationality, Sport sport, Country country) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.nationality = nationality;
        this.sport = sport;
        this.country = country;
    }
}
