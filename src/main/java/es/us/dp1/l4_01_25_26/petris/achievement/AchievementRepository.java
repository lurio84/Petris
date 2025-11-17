package es.us.dp1.l4_01_25_26.petris.achievement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.us.dp1.l4_01_25_26.petris.achievement.Achievement;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer>{
     
    List<Achievement> findAll();
    
    Optional<Achievement> findByName(String name);
}
