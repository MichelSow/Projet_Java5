package be.helb.misow.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// Classe représentant un pays dans le modèle de données
@Getter
@Setter
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Country {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique du pays
    private String name; // Nom du pays

    // Relation OneToMany avec l'entité Athlete
    // Les athlètes représentant ce pays
    @OneToMany(mappedBy = "country") // 'country' est le nom du champ dans Athlete faisant référence à Country
    @JsonIgnore
    private List<Athlete> athletes;

    // Relation OneToMany avec l'entité Team
    // Les équipes représentant ce pays
    @OneToMany(mappedBy = "country") // 'country' est le nom du champ dans Team faisant référence à Country
    private List<Team> teams;

    // Constructeur avec argument pour créer une instance de Country
    public Country(String name) {
        this.name = name;
    }


}
