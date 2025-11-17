// package es.us.dp1.l4_01_25_26.petris.statistics;

// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.when;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.FilterType;
// import org.springframework.http.MediaType;
// import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import es.us.dp1.l4_01_25_26.petris.configuration.SecurityConfiguration;
// import es.us.dp1.l4_01_25_26.petris.statistic.Achievement;
// import es.us.dp1.l4_01_25_26.petris.statistic.AchievementRestController;
// import es.us.dp1.l4_01_25_26.petris.statistic.AchievementService;
// import es.us.dp1.l4_01_25_26.petris.statistic.Metric;
// import es.us.dp1.l4_01_25_26.petris.user.Authorities;
// import es.us.dp1.l4_01_25_26.petris.user.User;
// import io.qameta.allure.Epic;
// import io.qameta.allure.Feature;
// import io.qameta.allure.Owner;

// /**
//  * Test class for {@link AchievementRestController}
//  **/

// @Epic("Statistics Module")
// @Feature("Achievements")
// @Owner("DP1_L4-1_Developers")
// @WebMvcTest(value = AchievementRestController.class, 
//  excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
//  classes = WebSecurityConfigurer.class), 
//  excludeAutoConfiguration = SecurityConfiguration.class)
// class AchievementRestControllerTests { 

//     private static final String BASE_URL = "/api/v1/achievements";

//     @SuppressWarnings("unused")
//     @Autowired
//     private AchievementRestController achievementRestController;
    
//     @MockBean
//     private AchievementService achievementService;

//     @Autowired
//     private MockMvc mockMvc; 

// 	@Autowired
// 	private ObjectMapper objectMapper;

//     private Achievement achievement;

//     @BeforeEach
//     void setup() {
//         achievement = new Achievement();
//         achievement.setId(1); 
//         achievement.setName("First of all...");
//         achievement.setDescription("Win your first match");
//         achievement.setBadgeImage("https://doctor-cv.com/wp-content/uploads/2020/08/gold-trophy-with-name-plate-winner-competition_68708-545-1.jpg");


//     }

//     ###################################### GET ######################################
    
//     ############ GET ALL ACHIEVEMENTS ############
//     @Test // (LIST) 200
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldReturnAllAchievements() throws Exception {
        
//         when(achievementService.getAchievements()).thenReturn(List.of(achievement));

//         mockMvc.perform(get(BASE_URL))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.size()").value(1));

//     }
//     ############ ERRORS ############

//     @Test // GET ALL (UNAUTHORIZED) 401
//     void shouldNotReturnAllAchievementUnauthorized() throws Exception {
//         mockMvc.perform(get(BASE_URL))
//         .andExpect(status().isUnauthorized());          
//     }


//     ############ GET ACHIEVEMENT BY ID ############
//     @Test // (ACHIEVEMENT) 200
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldReturnAchievementById() throws Exception {

//         when(achievementService.getById(1)).thenReturn(achievement);

//         mockMvc.perform(get(BASE_URL + "/{id}", 1))
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.id").value(achievement.getId()));

//     }
//     ############  ERRORS ############

//     @Test // (NOT FOUND ERROR) 404
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldNotReturnAchievementById() throws Exception {

//         mockMvc.perform(get(BASE_URL + "/{id}", 2))
//         .andExpect(status().isNotFound());
//     }
   
//     @Test // (UNAUTHORIZED) 401
//     void shouldNotReturnAchievementByIdUnauthorized() throws Exception {
//         mockMvc.perform(get(BASE_URL + "/{id}", 1))
//         .andExpect(status().isUnauthorized());          
//     }

//     ###################################### POST ######################################

//     ############ POST ACHIEVEMENT ############
//     @Test // (ACHIEVEMENT) 201
//     @WithMockUser(username = "player", roles = {"PLAYER"}) 
//     void shouldCreateAchievement() throws Exception {
        
//         Achievement newAchievement = new Achievement();
//         newAchievement.setId(2);
//         newAchievement.setName("Win Streak");
//         newAchievement.setDescription("Win your second match");
//         newAchievement.setBadgeImage("https://doctor-cv.com/wp-content/uploads/2020/08/gold-trophy-with-name-plate-winner-competition_68708-545-1.jpg");
//         newAchievement.setThreshold(2);
//         newAchievement.setMetric(Metric.VICTORIES_AS_GREEN);

//         when(achievementService.saveAchievement(newAchievement)).thenReturn(newAchievement);

//         mockMvc.perform(
//                     post(BASE_URL)
//                         .with(csrf())
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(newAchievement))
//                     )
//                     .andExpect(status().isCreated());
//     }
//     ############ POST ACHIEVEMENT ERRORS ############

//     ERROR POR VALIDACION¿? 500!=400
//     @Test // (VALIDATION ERROR) 400
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldNotCreateAchievement() throws Exception {
//         Achievement invalidAchievement = new Achievement();
//         invalidAchievement.setId(3);
//         invalidAchievement.setName(""); 
//         invalidAchievement.setDescription(""); 
//         invalidAchievement.setBadgeImage("");
//         invalidAchievement.setThreshold(-1.0); 
//         invalidAchievement.setMetric(null); 

//         mockMvc.perform(
//                 post(BASE_URL)
//                     .with(csrf())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(objectMapper.writeValueAsString(invalidAchievement))
//             )
//             .andExpect(status().isBadRequest());
//     }
    
//     ROLES NO APLICAN 204!=403 ¿?
//     @Test // (FORBIDDEN) 403
//     @WithMockUser(username = "123" ,roles = {"123"})    
//     void shouldNotCreateAchievementForbidden() throws Exception {
       
//         Achievement newAchievement = new Achievement();
//         newAchievement.setId(2);
//         newAchievement.setName("Win Streak");
//         newAchievement.setDescription("Win your second match");
//         newAchievement.setBadgeImage("https://doctor-cv.com/wp-content/uploads/2020/08/gold-trophy-with-name-plate-winner-competition_68708-545-1.jpg");
//         newAchievement.setThreshold(2.0);
//         newAchievement.setMetric(Metric.VICTORIES);

//         when(achievementService.saveAchievement(newAchievement)).thenReturn(newAchievement);

//         mockMvc.perform(
//                     post(BASE_URL)
//                         .with(csrf())
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(newAchievement))
//                     )
//                     .andExpect(status().isForbidden());
//     }

//     @Test // (UNAUTHORIZED) 401
//     void shouldNotCreateAchievementUnauthorized() throws Exception {
        
//         Achievement newAchievement = new Achievement();
//         newAchievement.setId(2);
//         newAchievement.setName("Win Streak");
//         newAchievement.setDescription("Win your second match");
//         newAchievement.setBadgeImage("https://doctor-cv.com/wp-content/uploads/2020/08/gold-trophy-with-name-plate-winner-competition_68708-545-1.jpg");
//         newAchievement.setThreshold(2);
//         newAchievement.setMetric(Metric.VICTORIES_AS_GREEN);

//         when(achievementService.saveAchievement(newAchievement)).thenReturn(newAchievement);

//         mockMvc.perform(
//                     post(BASE_URL)
//                         .with(csrf())
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(newAchievement))
//                     )
//                     .andExpect(status().isUnauthorized());
          
//     }
    
//     ###################################### PUT ######################################

//     ############ PUT ACHIEVEMENT ############
//     @Test // (ACHIEVEMENT) 204
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldUpdateAchievement() throws Exception {
        
//         achievement.setName("Updated Name");
//         achievement.setDescription("Updated description");

//         when(achievementService.getById(achievement.getId())).thenReturn(achievement);
//         when(achievementService.saveAchievement(achievement)).thenReturn(achievement);

//         mockMvc.perform(put(BASE_URL + "/{id}", achievement.getId())
//                 .with(csrf())
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(achievement)))
//                 .andExpect(status().isNoContent());
//     }

//     ############ PUT ACHIEVEMENT ERRORS ############

//     @Test // (NOT FOUND ERROR) 404
//     @WithMockUser(username = "player", roles = {"PLAYER"})
//     void shouldNotUpdateAchievementNotFound() throws Exception { 
//         achievement.setName("Updated Name");
//         achievement.setDescription("Updated description");
        
//         mockMvc.perform(put(BASE_URL + "/{id}", 999)
//             .with(csrf())
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(objectMapper.writeValueAsString(achievement)))
//             .andExpect(status().isNotFound());
//     }

//     @Test // (UNAUTHORIZED) 401
//     void shouldNotUpdateAchievementUnauthorized() throws Exception {
        
//         when(this.achievementService.getById(1)).thenReturn(achievement);

//         achievement.setName("Updated Name");
//         achievement.setDescription("Updated description");
        
//         mockMvc.perform(put(BASE_URL + "/{id}", 1)
//             .with(csrf())
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(objectMapper.writeValueAsString(achievement)))
//             .andExpect(status().isUnauthorized());
//     }

//     ###################################### DELETE ######################################
    
//     ############ DELETE ACHIEVEMENT ############
//     @Test // (True) 204
//     @WithMockUser(username = "player", roles = {"PLAYER"})    
//     void shouldDeleteAchievement() throws Exception {

//         when(this.achievementService.getById(1)).thenReturn(achievement);
// 		doNothing().when(this.achievementService).deleteAchievementById(1);

// 		mockMvc.perform(
//                         delete(BASE_URL + "/{id}", 1)
//                         .with(csrf())
//                         )
//                         .andExpect(status().isNoContent());
//     }
//     ############ DELETE ACHIEVEMENT ERRORS ############

//     @Test // (NOT FOUND ERROR) 404
//     @WithMockUser(username = "player", roles = {"PLAYER"})    
//     void shouldNotDeleteAchievement() throws Exception {
        
//         mockMvc.perform(
//                         delete(BASE_URL + "/{id}", 999)
//                         .with(csrf())
//                        )
//                        .andExpect(status().isNotFound());
//     }

//     @Test // (UNAUTHORIZED) 401
//     void shouldNotDeleteAchievementUnauthorized() throws Exception {
        
//         when(this.achievementService.getById(1)).thenReturn(achievement);

//         mockMvc.perform(
//                         delete(BASE_URL + "/{id}", 1)
//                         .with(csrf())
//                        )
//                        .andExpect(status().isUnauthorized());
//     }
// }
