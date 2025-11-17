package es.us.dp1.l4_01_25_26.petris.game.GameStatistics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {
}
