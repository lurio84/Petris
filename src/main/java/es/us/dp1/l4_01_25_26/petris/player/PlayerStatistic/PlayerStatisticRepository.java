package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PlayerStatisticRepository extends CrudRepository<PlayerStatistic, Integer> {
    
    List<PlayerStatistic> findAll();
    
    public Optional<PlayerStatistic> findById(int id);
}
