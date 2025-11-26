package es.us.dp1.l4_01_25_26.petris.statistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import es.us.dp1.l4_01_25_26.petris.game.GameStatistics.GameStatistics;
import es.us.dp1.l4_01_25_26.petris.game.GameStatistics.GameStatisticsService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;

@Epic("Statistics Module")
@Feature("Game Statistics")
@Owner("DP1_L4-1_Developers")
@SpringBootTest
@AutoConfigureTestDatabase
public class GameStatisticsServiceTests {

    @Autowired
    private GameStatisticsService gameStatisticsService;

    // ###################################### GET
    // ######################################

    // ########### GET ALL ###########
    @Test
    void shouldGetAllGameStatistics() {
        List<GameStatistics> gameStatistics = gameStatisticsService.findAll();
        assertThat(gameStatistics).isNotNull();
        assertThat(gameStatistics.size()).isGreaterThanOrEqualTo(0);
    }

    // ########### GET BY ID ###########
    @Test // 200
    void shouldGetGameStatisticsById() {
        GameStatistics gameStatistics = gameStatisticsService.findById(1).orElse(null);
        assertThat(gameStatistics).isNotNull();
        assertThat(gameStatistics.getId()).isEqualTo(1);
    }

    @Test // 404
    void shouldNotGetByIdNotFound() {
        GameStatistics result = gameStatisticsService.findById(999).orElse(null); // id que no existe
        assertNull(result);
    }

    // ###################################### POST
    // ######################################
    @Test // 204
    void shouldPostGameStatistics() {
        GameStatistics newStats = new GameStatistics();
        newStats.setTurnsPlayed(10);
        newStats.setTotalBacteries(50);
        newStats.setTotalSarcines(30);
        newStats.setWinner("green");
        newStats.setGreenPlayerBacteries(30);
        newStats.setGreenPlayerSarcines(20);
        newStats.setPurplePlayerBacteries(20);
        newStats.setPurplePlayerSarcines(10);

        GameStatistics savedStats = gameStatisticsService.save(newStats);
        assertThat(savedStats).isNotNull();
        assertThat(savedStats.getId()).isNotNull();
        assertThat(savedStats.getWinner()).isEqualTo("green");
    }

    @Test // 400
    void shouldNotPostGameStatisticsBadRequest() {
        GameStatistics newStats = new GameStatistics();
        newStats.setTurnsPlayed(-5); // Valor inválido
        newStats.setTotalBacteries(50);
        newStats.setTotalSarcines(30);
        newStats.setWinner("green");
        newStats.setGreenPlayerBacteries(30);
        newStats.setGreenPlayerSarcines(20);
        newStats.setPurplePlayerBacteries(20);
        newStats.setPurplePlayerSarcines(10);

        assertThatThrownBy(() -> {
            gameStatisticsService.save(newStats);
        });
    }

    // ###################################### DELETE
    // ######################################

    @Test // 204
    void shouldDeleteGameStatistics() {
        int initialSize = gameStatisticsService.findAll().size();

        GameStatistics newStats = new GameStatistics();
        newStats.setTurnsPlayed(15);
        newStats.setTotalBacteries(60);
        newStats.setTotalSarcines(40);
        newStats.setWinner("purple");
        newStats.setGreenPlayerBacteries(25);
        newStats.setGreenPlayerSarcines(15);
        newStats.setPurplePlayerBacteries(35);
        newStats.setPurplePlayerSarcines(25);

        GameStatistics savedStats = gameStatisticsService.save(newStats);
        assertThat(gameStatisticsService.findAll().size()).isEqualTo(initialSize + 1);

        gameStatisticsService.delete(savedStats.getId());
        assertThat(gameStatisticsService.findAll().size()).isEqualTo(initialSize);
        assertThat(gameStatisticsService.findById(savedStats.getId()));
    }
}
