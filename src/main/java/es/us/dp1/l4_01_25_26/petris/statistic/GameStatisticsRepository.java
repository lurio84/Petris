package es.us.dp1.l4_01_25_26.petris.statistic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {
}
