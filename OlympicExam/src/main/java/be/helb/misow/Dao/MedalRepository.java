package be.helb.misow.Dao;

import be.helb.misow.Model.Medal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedalRepository extends JpaRepository<Medal, Long> {

    Medal findByType(String name);
}
