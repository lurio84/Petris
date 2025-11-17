package es.us.dp1.l4_01_25_26.petris.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.l4_01_25_26.petris.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository){
        this.achievementRepository = achievementRepository;
    }

    @Transactional(readOnly = true)
    public List<Achievement> getAchievements() {
        return achievementRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Achievement getById(int id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Achievement with id " + id + " not found."));
    }

    @Transactional(readOnly = true)
    public Achievement getAchievementByName(String name) {
        return achievementRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Achievement " + name + " not found."));
    }

    @Transactional
    public Achievement saveAchievement(@Valid Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    @Transactional
    public Achievement updateAchievement(int id, @Valid Achievement achievement) {
        Achievement existing = getById(id); 
        existing.setName(achievement.getName());
        existing.setDescription(achievement.getDescription());
        existing.setBadgeImage(achievement.getBadgeImage());
        existing.setMetric(achievement.getMetric());
        existing.setThreshold(achievement.getThreshold());

        return achievementRepository.save(existing);
    }

    @Transactional
    public void deleteAchievementById(int id) {
        getById(id); 
        achievementRepository.deleteById(id);
    }
}
