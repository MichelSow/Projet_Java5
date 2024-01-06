package be.helb.misow.Dao;

import be.helb.misow.Model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor,Long> {


    Sponsor findByName(String name);

}
