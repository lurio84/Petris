// package es.us.dp1.l4_01_25_26.petris.player.PlayerTests;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.assertThatThrownBy;
// import java.util.List;
// import java.util.List;
// import java.util.Set;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import es.us.dp1.l4_01_25_26.petris.player.Player;
// import es.us.dp1.l4_01_25_26.petris.player.PlayerService;
// import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatistic;
// import es.us.dp1.l4_01_25_26.petris.user.Authorities;
// import es.us.dp1.l4_01_25_26.petris.user.AuthoritiesRepository;
// import es.us.dp1.l4_01_25_26.petris.user.AuthoritiesService;
// import es.us.dp1.l4_01_25_26.petris.user.User;
// import io.qameta.allure.Feature;
// import io.qameta.allure.Owner;

// @Feature("Player Management")
// @Owner("DP1_L4-1_Developers")
// @SpringBootTest
// @AutoConfigureTestDatabase
// public class PlayerServiceTests {

//     @Autowired
//     private PlayerService playerService;

//     @Autowired
//     private AuthoritiesService authoritiesService;

//     //###################################### GET ######################################
//     @Test
//     public void shouldGetAllPlayers() {
//         List<Player> players = playerService.getAllPlayers();
//         assertThat(players).isNotNull();
//         assertThat(players.size()).isGreaterThanOrEqualTo(0);
//     }

//     //########### GET BY ID ###########

//     @Test
//     public void shouldGetPlayerById() {
//         Player player = playerService.getById(4);
//         assertThat(player).isNotNull();
//         assertThat(player.getId()).isEqualTo(4);
//     }

//     @Test
//     public void shouldNotGetPlayerByIdNotFound() {
//         Player player = playerService.getById(999); // id not found
//         assertThat(player).isNull();
//     }

//     @Test
//     public void shouldCreatePlayer() {
//         Player player = new Player();
//         player.setAvatar("avatar.png");
//         player.setProfileInfo("Test profile");
//         player.setIsOnline(false);
//         player.setFriends(Set.of("friend1", "friend2"));
//         player.setStats(PlayerStatistic.empty());
//         player.setLastName("Cogabonga");
//         player.setFirstName("Test");
//         player.setAchievements(List.of());

//         Authorities authority = authoritiesService.findByAuthority("PLAYER");
//         User user = new User();
//         user.setUsername("testuser");
//         user.setPassword("password");
//         user.setAuthority(authority); 
//         player.setUser(user);
        
//         Player savedPlayer = playerService.savePlayer(player);
//         assertThat(savedPlayer).isNotNull();

//         Player fetchedPlayer = playerService.getById(savedPlayer.getId());
//         assertThat(fetchedPlayer).isNotNull();
//         assertThat(fetchedPlayer.getId()).isEqualTo(savedPlayer.getId());
//     }

//     @Test
//     public void shouldNotCreatePlayerBadRequest() {
//         Player player = new Player();
//         player.setAvatar("avatar.png");
//         player.setProfileInfo("Test profile");
//         player.setIsOnline(false);
//         player.setFriends(Set.of("friend1", "friend2"));
//         player.setStats(PlayerStatistic.empty());
//         player.getStats().setFriends(-10); // Invalid value

//         assertThatThrownBy(() -> playerService.savePlayer(player));
//     }

//     @Test
//     public void shouldUpdatePlayer() {
//         Player player = playerService.getById(4);
//         assertThat(player).isNotNull();
//         player.getStats().setGamesAsPurple(player.getStats().getGamesAsPurple() + 1);
//         Player updatedPlayer = playerService.savePlayer(player);
//         assertThat(updatedPlayer).isNotNull();
//         assertThat(updatedPlayer.getStats().getGamesAsPurple()).isEqualTo(player.getStats().getGamesAsPurple());}

//     @Test
//     public void shouldNotUpdatePlayerBadRequest() {
//         Player player = playerService.getById(4);
//         assertThat(player).isNotNull();
//         player.getStats().setFriends(-5); // Invalid value
//         assertThatThrownBy(() -> playerService.savePlayer(player));
//     }

//     @Test
//     public void shouldDeletePlayer() {
//         playerService.deletePlayerById(4);
//         Player player = playerService.getById(4);
//         assertThat(player).isNull();
//     }
// }