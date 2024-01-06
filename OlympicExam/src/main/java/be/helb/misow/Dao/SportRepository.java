package be.helb.misow.Dao;

import be.helb.misow.Model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository  extends JpaRepository<Sport,Long> {

    Sport findByName(String name);
}
