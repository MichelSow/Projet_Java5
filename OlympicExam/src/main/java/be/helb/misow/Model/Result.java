package be.helb.misow.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Classe représentant un résultat sportif dans le modèle de données
@Getter // Génère automatiquement les setters pour tous les champs
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Result {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique du résultat

    // Relation ManyToOne avec l'entité Athlete
    @ManyToOne
    @JoinColumn(name = "athlete_id") // Clé étrangère pour Athlete
    private Athlete athlete; // Athlète associé à ce résultat

    // Relation ManyToOne avec l'entité Team
    @ManyToOne
    @JoinColumn(name = "team_id") // Clé étrangère pour Team
    private Team team; // Équipe associée à ce résultat

    // Relation ManyToOne avec l'entité Sport
    @ManyToOne
    @JoinColumn(name = "sport_id") // Clé étrangère pour Sport
    private Sport sport; // Sport associé à ce résultat

    private int rank; // Classement de l'athlète ou de l'équipe dans l'épreuve
    private double score; // Score réalisé par l'athlète ou l'équipe

    // Constructeur avec arguments pour créer une instance de Result
    public Result(Athlete athlete, Team team, Sport sport, int rank, double score) {
        this.athlete = athlete;
        this.team = team;
        this.sport = sport;
        this.rank = rank;
        this.score = score;
    }


}
