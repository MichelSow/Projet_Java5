package be.helb.misow.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Classe représentant un lieu dans le modèle de données
@Getter
@Setter
@NoArgsConstructor // Génère un constructeur sans arguments
@Entity
public class Place {

    @Id // Marque le champ comme clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie de génération automatique de l'ID
    private Long id; // Identifiant unique du lieu
    private String name; // Nom du lieu
    private String address; // Adresse du lieu
    private float capacity; // Capacité du lieu (par exemple, nombre de sièges)

    // Constructeur avec arguments pour créer une instance de Place
    public Place(String name, String address, float capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }


}
