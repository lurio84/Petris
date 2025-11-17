// package es.us.dp1.l4_01_25_26.petris.statistics;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.assertj.core.api.Assertions.assertThatThrownBy;
// import static org.junit.Assert.assertNull;

// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;

// import es.us.dp1.l4_01_25_26.petris.statistic.Achievement;
// import es.us.dp1.l4_01_25_26.petris.statistic.AchievementService;
// import es.us.dp1.l4_01_25_26.petris.statistic.Metric;
// import io.qameta.allure.Epic;
// import io.qameta.allure.Feature;
// import io.qameta.allure.Owner;

// @Epic("Statistics Module")
// @Feature("Achievements")
// @Owner("DP1_L4-1_Developers")
// @SpringBootTest
// @AutoConfigureTestDatabase
// class AchievementServiceTests { 

//     @Autowired
//     private AchievementService achievementService;

//     //###################################### GET ######################################

//     //########### GET ALL ###########
//     @Test
//     void shouldGetAllAchievements() {
//         List<Achievement> achievements = achievementService.getAchievements();
//         assertThat(achievements).isNotNull();
//         assertThat(achievements.size()).isGreaterThanOrEqualTo(0);
//     }
    
//     //########### GET BY ID ###########
//     @Test //200
//     void shouldGetAchievementsById() {
//         Achievement achievement = achievementService.getById(1); 
//         assertThat(achievement).isNotNull();
//         assertThat(achievement.getId()).isEqualTo(1);
//     }
    
//     @Test //404
//     void shouldNotGetByIdNotFound() {
//         Achievement result = achievementService.getById(999); // id que no existe
//         assertNull(result);
//     }
  
//     //########### GET BY NAME ###########
//     @Test //200
//     void shouldGetByName() {
//         Achievement achievement = achievementService.getAchievementByName("Principiante"); 
//         assertThat(achievement).isNotNull();
//         assertThat(achievementService.getAchievementByName("Principiante").getName()).isEqualTo("Principiante");
//     }
//     @Test //404
//     void shouldNotGetByNameNotFound() {
//         Achievement achievement = achievementService.getAchievementByName("##########");
//         assertThat(achievement).isNull();
//     }
   
//     // ###################################### POST ######################################

//     @Test // 204
//     void shouldPostAchievement() {
//         Achievement newAchievement = new Achievement();
//         newAchievement.setName("Win Streak");
//         newAchievement.setDescription("Win your second match");
//         newAchievement.setBadgeImage("image1.png");
//         newAchievement.setThreshold(2);
//         newAchievement.setMetric(Metric.VICTORIES_AS_GREEN);

//         Achievement savedAchievement = achievementService.saveAchievement(newAchievement);

//         assertThat(savedAchievement.getId()).isNotNull();

//         Achievement foundAchievement = achievementService.getById(savedAchievement.getId());
//         assertThat(foundAchievement).isNotNull();
//         assertThat(foundAchievement.getId()).isEqualTo(savedAchievement.getId());
//         assertThat(foundAchievement.getName()).isEqualTo("Win Streak");
//     }
   
//     @Test // 400
//     void shouldNotPostAchievementBadRequest() {
//         Achievement invalidAchievement = new Achievement();
//         invalidAchievement.setName(" ");
//         invalidAchievement.setDescription("Win your second match");
//         invalidAchievement.setBadgeImage("https://doctor-cv.com/wp-content/uploads/2020/08/gold-trophy-with-name-plate-winner-competition_68708-545-1.jpg");
//         invalidAchievement.setThreshold(2);
//         invalidAchievement.setMetric(Metric.VICTORIES_AS_GREEN);

//         assertThatThrownBy(() -> achievementService.saveAchievement(invalidAchievement));
//     }

//     // ###################################### DELETE ######################################

//     @Test //204
//     void shouldDeleteAchievement() {
//         int initialSize = achievementService.getAchievements().size();
        
//         Achievement newAchievement = new Achievement();
//         newAchievement.setName("Win Streak");
//         newAchievement.setDescription("Win your second match");
//         newAchievement.setBadgeImage("image1.png");
//         newAchievement.setThreshold(2);
//         newAchievement.setMetric(Metric.VICTORIES_AS_GREEN);

//         Achievement savedAchievement = achievementService.saveAchievement(newAchievement);
//         assertThat(achievementService.getAchievements().size()).isEqualTo(initialSize + 1);

//         achievementService.deleteAchievementById(savedAchievement.getId());
//         assertThat(achievementService.getAchievements().size()).isEqualTo(initialSize);
//         assertThat(achievementService.getById(savedAchievement.getId())).isNull();
//     }
// }
