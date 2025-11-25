package es.us.dp1.l4_01_25_26.petris.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/achievements")
@Tag(name = "Achievements", description = "The Achievements management API")
@SecurityRequirement(name = "bearerAuth")
public class AchievementRestController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementRestController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    public List<Achievement> getAll() {
        return achievementService.getAchievements();
    }

    @GetMapping("/{id}")
    public Achievement getById(@PathVariable int id) {
        return achievementService.getById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Achievement create(@RequestBody @Valid Achievement achievement) {
        return achievementService.saveAchievement(achievement);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody @Valid Achievement achievement) {
        achievementService.updateAchievement(id, achievement);
    }
	

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        achievementService.deleteAchievementById(id);
    }
}
