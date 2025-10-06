package es.us.dp1.l4_01_25_26.petris.statistic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.us.dp1.l4_01_25_26.petris.statistic.Achievement;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer>{
     
    List<Achievement> findAll();
    
    public Achievement findByName(String name);
}
