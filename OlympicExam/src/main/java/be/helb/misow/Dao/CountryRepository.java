package be.helb.misow.Dao;

import be.helb.misow.Model.Country;
import be.helb.misow.Model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String name);
}
