package es.us.dp1.l4_01_25_26.petris.player.PlayerStatisticTests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.us.dp1.l4_01_25_26.petris.configuration.SecurityConfiguration;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatistic;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatisticRestController;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatisticService;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.Team;
import lombok.With;

@WebMvcTest(value = PlayerStatisticRestController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
    classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class)
class PlayerStatisticRestControllerTests {

    private static final String BASE_URL = "/api/v1/playerstatistics";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerStatisticService playerStatisticService;

    private PlayerStatistic ps;

    @BeforeEach
    void setUp() {
        ps = PlayerStatistic.empty();
        ps.setId(1);
        ps.setGamesPlayed(10);
        ps.setGamesWon(7);
        ps.setFavouriteTeam(Team.GREEN);
    }

    //###################################### GET ######################################

    //############ GET ALL PLAYER'S STATISTICS ############

    @Test // GET ALL 200
    @WithMockUser(username = "player", roles = {"PLAYER"})
    void shouldReturnAllPlayerStatistics() throws Exception {
        List<PlayerStatistic> stats = List.of(ps);
        when(playerStatisticService.getAllPlayerStatistics()).thenReturn(stats);

        mockMvc.perform(get(BASE_URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1));
    }

    @Test // GET ALL (UNAUTHORIZED) 401
    void shouldNotReturnAllPlayerStatisticsUnauthorized() throws Exception {
        mockMvc.perform(get(BASE_URL))
            .andExpect(status().isUnauthorized());
    }

    //############ GET A PLAYER STATISTICS BY ID ############

    @Test // GET BY ID 200
    @WithMockUser(username = "player", roles = {"PLAYER"})
    void shouldReturnPlayerStatisticById() throws Exception {
        
        when(playerStatisticService.getById(1)).thenReturn(ps);

        mockMvc.perform(get(BASE_URL + "/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.gamesPlayed").value(10))
            .andExpect(jsonPath("$.gamesWon").value(7))
            .andExpect(jsonPath("$.favouriteTeam").value("GREEN"));
    }

    @Test // GET BY ID (UNAUTHORIZED) 401
    void shouldNotReturnPlayerStatisticByIdUnauthorized() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1"))
            .andExpect(status().isUnauthorized());
    }

    @Test // GET BY ID (NOT FOUND) 404
    @WithMockUser(username = "player", roles = {"PLAYER"})
    void shouldNotReturnPlayerStatisticByIdNotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999"))
            .andExpect(status().isNotFound());
    }

    //###################################### POST #####################################
    
    //############ POST A PLAYER STATISTICS ############

    @Test // POST 201
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldCreatePlayerStatistic() throws Exception {
        PlayerStatistic toCreate = PlayerStatistic.empty(); // fill required fields
        toCreate.setId(1);
        // copy other fields from toCreate if needed
        when(playerStatisticService.savePlayerStatistic(org.mockito.ArgumentMatchers.any(PlayerStatistic.class)))
            .thenReturn(toCreate);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(toCreate)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test // POST (UNAUTHORIZED) 401
    void shouldNotCreatePlayerStatisticUnauthorized() throws Exception {
        when(playerStatisticService.savePlayerStatistic(org.mockito.ArgumentMatchers.any(PlayerStatistic.class)))
            .thenReturn(ps);
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(ps)))
            .andExpect(status().isUnauthorized());
    }
    
    //###################################### PUT ######################################

    //############ PUT PLAYER'S STATISTICS ############

    @Test // PUT 204
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldUpdatePlayerStatistic() throws Exception {
        PlayerStatistic toUpdate = ps;
        toUpdate.setGamesPlayed(15);
        when(playerStatisticService.getById(1)).thenReturn(ps);
        when(playerStatisticService.updatePlayerStatistic(org.mockito.ArgumentMatchers.any(PlayerStatistic.class), org.mockito.ArgumentMatchers.eq(1)))
            .thenReturn(toUpdate);

        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(toUpdate)))
            .andExpect(status().isNoContent());
    }

    @Test // PUT (NOT FOUND) 404
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldNotUpdatePlayerStatisticNotFound() throws Exception {
        ps.setFriends(23);
        
        mockMvc.perform(put(BASE_URL + "/999")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(ps)))
            .andExpect(status().isNotFound());
    }

    @Test // PUT (UNAUTHORIZED) 401
    void shouldNotUpdatePlayerStatisticUnauthorized() throws Exception {
        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(ps)))
            .andExpect(status().isUnauthorized());
    }

    //###################################### DELETE ######################################
    @Test // DELETE 204
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldDeletePlayerStatistic() throws Exception {
        when(playerStatisticService.getById(1)).thenReturn(ps);
        doNothing().when(this.playerStatisticService).deletePlayerStatisticById(1);
        mockMvc.perform(delete(BASE_URL + "/1")
            .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test // DELETE (NOT FOUND) 404
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldNotDeletePlayerStatisticNotFound() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/999")
            .with(csrf()))
            .andExpect(status().isNotFound()); 
    }

    @Test // DELETE (UNAUTHORIZED) 401
    void shouldNotDeletePlayerStatisticUnauthorized() throws Exception {
        
        when(this.playerStatisticService.getById(1)).thenReturn(ps);
        
        mockMvc.perform(delete(BASE_URL + "/1")
            .with(csrf()))
            .andExpect(status().isUnauthorized());
    }
}