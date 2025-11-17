package es.us.dp1.l4_01_25_26.petris.game.GameStatistics;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class GameStatisticsService {
    
    private final GameStatisticsRepository repository;

    public GameStatisticsService(GameStatisticsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<GameStatistics> findAll() {
        return (List<GameStatistics>) repository.findAll();
    }

    @Transactional
    public Optional<GameStatistics> findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public GameStatistics save(GameStatistics stats) {
        if (stats.getWinner() == null) stats.setWinner("pending");
        return repository.save(stats);
    }

    @Transactional
    public Optional<GameStatistics> update(Integer id, GameStatistics data) {
        return repository.findById(id).map(existing -> {
            existing.setTurnsPlayed(data.getTurnsPlayed());
            existing.setTotalBacteries(data.getTotalBacteries());
            existing.setTotalSarcines(data.getTotalSarcines());
            existing.setWinner(data.getWinner());
            existing.setGreenPlayerBacteries(data.getGreenPlayerBacteries());
            existing.setGreenPlayerSarcines(data.getGreenPlayerSarcines());
            existing.setPurplePlayerBacteries(data.getPurplePlayerBacteries());
            existing.setPurplePlayerSarcines(data.getPurplePlayerSarcines());
            return repository.save(existing);
        });
    }

    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
