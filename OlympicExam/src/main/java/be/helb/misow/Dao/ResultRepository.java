package be.helb.misow.Dao;

import be.helb.misow.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {


    Result findByRank(int i);
}
