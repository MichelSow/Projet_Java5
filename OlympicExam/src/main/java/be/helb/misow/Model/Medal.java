package be.helb.misow.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Classe représentant une médaille dans le modèle de données
@Getter
@Setter
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Medal {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique de la médaille
    private String type; // Type de médaille (Or, Argent, Bronze)

    // Relation ManyToOne avec l'entité Sport
    @ManyToOne
    @JoinColumn(name = "sport_id") // Clé étrangère pour Sport
    private Sport sport; // Sport associé à la médaille

    // Relation ManyToOne avec l'entité Athlete
    @ManyToOne
    @JoinColumn(name = "athlete_id") // Clé étrangère pour Athlete
    private Athlete athlete; // Athlète ayant remporté la médaille

    // Relation ManyToOne avec l'entité Team
    @ManyToOne
    @JoinColumn(name = "team_id") // Clé étrangère pour Team
    private Team team; // Équipe ayant remporté la médaille

    // Constructeur avec arguments pour créer une instance de Medal
    public Medal(String type, Sport sport, Athlete athlete, Team team) {
        this.type = type;
        this.sport = sport;
        this.athlete = athlete;
        this.team = team;
    }


}
