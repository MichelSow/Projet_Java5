package be.helb.misow.Dao;

import be.helb.misow.Model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    // Méthode que j'ai creé qui va permettre de trouver un athlete par age et genre
    List<Athlete> findByAgeAndGender(int age, char gender);

    Athlete findByName(String name);
}
