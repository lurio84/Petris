package es.us.dp1.l4_01_25_26.petris.statistic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PlayerStadisticRepository extends CrudRepository<PlayerStadistic, Integer> {
    
    List<PlayerStadistic> findAll();
    
    public Optional<PlayerStadistic> findById(int id);

    public PlayerStadistic save(PlayerStadistic ps);
}
