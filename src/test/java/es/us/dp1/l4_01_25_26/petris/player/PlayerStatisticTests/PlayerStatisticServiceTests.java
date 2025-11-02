package es.us.dp1.l4_01_25_26.petris.player.PlayerStatisticTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatistic;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatisticService;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;

@Feature("Statistics Management")
@Owner("DP1_L4-1_Developers")
@SpringBootTest
@AutoConfigureTestDatabase
public class PlayerStatisticServiceTests {
    @Autowired
    private PlayerStatisticService playerStatisticService;
    
    //###################################### GET ######################################
    @Test
    public void shouldGetAllPlayerStatistics() {
        List<PlayerStatistic> stats = playerStatisticService.getAllPlayerStatistics();
        assertThat(stats).isNotNull();
        assertThat(stats.size()).isGreaterThanOrEqualTo(0);
    }

     //########### GET BY ID ###########

    @Test
    public void shouldGetPlayerStatisticById() {
            PlayerStatistic stat = playerStatisticService.getById(5); 
            assertThat(stat).isNotNull();
            assertThat(stat.getId()).isEqualTo(5);
    }

    @Test
    public void shouldNotGetPlayerStatisticByIdNotFound() {
            PlayerStatistic stat = playerStatisticService.getById(999); // id
            assertThat(stat).isNull();

    }

    @Test
    public void shouldCreatePlayerStatistic() {
        PlayerStatistic ps = PlayerStatistic.empty();

        PlayerStatistic savedPs = playerStatisticService.savePlayerStatistic(ps);
        assertThat(savedPs).isNotNull();
        
        PlayerStatistic fetchedPs = playerStatisticService.getById(savedPs.getId());
        assertThat(fetchedPs).isNotNull();
        assertThat(fetchedPs.getId()).isEqualTo(savedPs.getId());
        assertThat(fetchedPs.getGamesPlayed()).isEqualTo(savedPs.getGamesPlayed());
    }

    @Test
    public void shouldNotCreatePlayerStatisticBadRequest() {
        PlayerStatistic ps = PlayerStatistic.empty();
        ps.setFriends(-10); // Invalid value

        assertThatThrownBy(() -> playerStatisticService.savePlayerStatistic(ps));
    }

    @Test
    public void shouldUpdatePlayerStatistic() {
        PlayerStatistic ps = playerStatisticService.getById(5);
        assertThat(ps).isNotNull();
        ps.setFriends(ps.getFriends() + 1);
        PlayerStatistic updatedPs = playerStatisticService.savePlayerStatistic(ps);
        assertThat(updatedPs).isNotNull();
        assertThat(updatedPs.getFriends()).isEqualTo(ps.getFriends());
    }

    @Test
    public void shouldNotUpdatePlayerStatisticBadRequest() {
        PlayerStatistic ps = playerStatisticService.getById(5);
        assertThat(ps).isNotNull();
        ps.setFriends(-5); // Invalid value
        assertThatThrownBy(() -> playerStatisticService.savePlayerStatistic(ps));
    }

    @Test
    public void shouldDeletePlayerStatistic() {
        playerStatisticService.deletePlayerStatisticById(4);
        PlayerStatistic ps = playerStatisticService.getById(4);
        assertThat(ps).isNull();
    }
}
