package be.helb.misow.Dao;

import be.helb.misow.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {


    Team findByName(String name);
}
