package es.us.dp1.l4_01_25_26.petris.statistic;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/statistics")
public class GameStatisticsRestController {

    private final GameStatisticsService gameStatisticsService;

    public GameStatisticsRestController(GameStatisticsService gameStatisticsService) {
        this.gameStatisticsService = gameStatisticsService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GameStatistics> create(@RequestBody GameStatistics newStats) {
        GameStatistics saved = gameStatisticsService.save(newStats);
        return ResponseEntity.ok(saved);
    }
    
    // READ (all)
    @GetMapping
    public List<GameStatistics> findAll() {
        return gameStatisticsService.findAll();
    }

    // READ (by id)
    @GetMapping("/{id}")
    public ResponseEntity<GameStatistics> findById(@PathVariable Integer id) {
        return gameStatisticsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GameStatistics> update(@PathVariable Integer id,
                                                 @RequestBody GameStatistics body) {
        return gameStatisticsService.update(id, body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (gameStatisticsService.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
