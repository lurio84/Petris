// package es.us.dp1.l4_01_25_26.petris.player.PlayerTests;

// import java.util.List;
// import java.util.Set;

// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.when;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.FilterType;
// import org.springframework.http.MediaType;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import es.us.dp1.l4_01_25_26.petris.configuration.SecurityConfiguration;
// import es.us.dp1.l4_01_25_26.petris.player.Player;
// import es.us.dp1.l4_01_25_26.petris.player.PlayerRestController;
// import es.us.dp1.l4_01_25_26.petris.player.PlayerService;
// import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatistic;
// import es.us.dp1.l4_01_25_26.petris.user.Authorities;
// import es.us.dp1.l4_01_25_26.petris.user.AuthoritiesService;
// import es.us.dp1.l4_01_25_26.petris.user.User;
// import lombok.With;

// @WebMvcTest(value= PlayerRestController.class,
//     excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
//     classes = WebSecurityConfigurer.class),
//     excludeAutoConfiguration = SecurityConfiguration.class)
// public class PlayerRestControllerTest {
//     private static final String BASE_URL = "/api/v1/player";
    
//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @MockBean
//     private PlayerService playerService;
//     @MockBean
//     private AuthoritiesService authoritiesService;

//     Player player = new Player();
//     @BeforeEach
//     void setUp() {
//         player.setId(1);
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
//     }

//     //###################################### GET ######################################

//     //############ GET ALL PLAYER'S STATISTICS ############

//     @Test // GET ALL 200
//     @WithMockUser(username = "admin1", roles = {"ADMIN"})
//     void testGetAllPlayers() throws Exception {
//         when(playerService.getAllPlayers()).thenReturn(List.of(player));

//         // Perform the GET request
//         mockMvc.perform(get(BASE_URL))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.size()").value(1));
//     }

//     @Test // GET ALL (UNAUTHORIZED) 401
//     void shouldNotReturnAllPlayersUnauthorized() throws Exception {
//         mockMvc.perform(get(BASE_URL))
//             .andExpect(status().isUnauthorized());
//     }

//      //############ GET A PLAYER STATISTICS BY ID ############

//     @Test // GET BY ID 200
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldReturnPlayerById() throws Exception {
//         when(playerService.getById(1)).thenReturn(player);

//         mockMvc.perform(get(BASE_URL + "/1")) // Corrected URL to include the ID
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(1))
//                 .andExpect(jsonPath("$.firstName").value("Test"))
//                 .andExpect(jsonPath("$.lastName").value("Cogabonga"));
//     }

//     @Test // GET BY ID (UNAUTHORIZED) 401
//     void shouldNotReturnPlayerByIdUnauthorized() throws Exception {
//         mockMvc.perform(get(BASE_URL + "/1"))
//             .andExpect(status().isUnauthorized());
//     }

//     @Test // GET BY ID (NOT FOUND) 404
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldNotReturnPlayerByIdNotFound() throws Exception {
//         mockMvc.perform(get(BASE_URL + "/999"))
//             .andExpect(status().isNotFound());
//     }

//     //###################################### POST #####################################
    
//     //############ POST PLAYER ############

//     @Test // POST 201
//     @WithMockUser(username = "admin", roles = {"ADMIN"})
//     void shouldCreatePlayer() throws Exception {
//         Player toCreate = player;
        
//         // copy other fields from toCreate if needed
//         when(playerService.savePlayer(org.mockito.ArgumentMatchers.any(Player.class)))
//             .thenReturn(toCreate);

//         mockMvc.perform(post(BASE_URL)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .with(csrf())
//                 .content(objectMapper.writeValueAsString(toCreate)))
//             .andExpect(status().isCreated())
//             .andExpect(jsonPath("$.id").value(1));
//     }

//     @Test // POST (UNAUTHORIZED) 401
//     void shouldNotCreatePlayerStatisticUnauthorized() throws Exception {
//         when(playerService.savePlayer(org.mockito.ArgumentMatchers.any(Player.class)))
//             .thenReturn(player);
//         mockMvc.perform(post(BASE_URL)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .with(csrf())
//                 .content(objectMapper.writeValueAsString(player)))
//             .andExpect(status().isUnauthorized());
//     }

//     //###################################### PUT ######################################

//     //############ PUT PLAYER ############

//     @Test // PUT 204
//     @WithMockUser(username = "admin", roles = {"ADMIN"})
//     void shouldUpdatePlayer() throws Exception {
//         Player toUpdate = player;
//         toUpdate.setFirstName("Updated");
//         when(playerService.getById(1)).thenReturn(player);
//         when(playerService.updatePlayer(org.mockito.ArgumentMatchers.any(Player.class), org.mockito.ArgumentMatchers.eq(1)))
//             .thenReturn(toUpdate);
//         mockMvc.perform(put(BASE_URL + "/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .with(csrf())
//                 .content(objectMapper.writeValueAsString(toUpdate)))
//             .andExpect(status().isNoContent());
//     }


//     @Test // PUT (NOT FOUND) 404
//     @WithMockUser(username = "admin", roles = {"ADMIN"})
//     void shouldNotUpdatePlayerNotFound() throws Exception {
//         player.setFirstName("Updated");

//         mockMvc.perform(put(BASE_URL + "/999")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .with(csrf())
//                 .content(objectMapper.writeValueAsString(player)))
//             .andExpect(status().isNotFound());
//     }



//     @Test // PUT (UNAUTHORIZED) 401
//     void shouldNotUpdatePlayerUnauthorized() throws Exception {
//         player.setFirstName("Updated");

//         mockMvc.perform(put(BASE_URL + "/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .with(csrf())
//                 .content(objectMapper.writeValueAsString(player)))
//             .andExpect(status().isUnauthorized());
//     }
    
//     //###################################### DELETE ######################################

//     //############ DELETE PLAYER BY ID ############

//     @Test // DELETE 204
//     @WithMockUser(username = "admin", roles = {"ADMIN"})
//     void shouldDeletePlayerById() throws Exception {
//         when(playerService.getById(1)).thenReturn(player);
//         doNothing().when(playerService).deletePlayerById(1);

//         mockMvc.perform(delete(BASE_URL + "/1")
//                 .with(csrf()))
//             .andExpect(status().isNoContent());
//     }

//     @Test // DELETE (UNAUTHORIZED) 401
//     void shouldNotDeletePlayerByIdUnauthorized() throws Exception {
//         doNothing().when(playerService).deletePlayerById(1);

//         mockMvc.perform(delete(BASE_URL + "/1")
//                 .with(csrf()))
//             .andExpect(status().isUnauthorized());
//     }
// }