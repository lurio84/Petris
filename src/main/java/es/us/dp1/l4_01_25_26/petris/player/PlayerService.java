package es.us.dp1.l4_01_25_26.petris.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.l4_01_25_26.petris.achievement.Achievement;
import es.us.dp1.l4_01_25_26.petris.achievement.AchievementRepository;
import es.us.dp1.l4_01_25_26.petris.player.dto.CreateEditPlayerDTO;
import es.us.dp1.l4_01_25_26.petris.player.dto.PlayerDTO;
import es.us.dp1.l4_01_25_26.petris.user.User;
import es.us.dp1.l4_01_25_26.petris.user.authorities.Authorities;
import es.us.dp1.l4_01_25_26.petris.user.authorities.AuthoritiesRepository;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final AchievementRepository achievementRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, AchievementRepository achievementRepository,
            AuthoritiesRepository authoritiesRepository) {
        this.playerRepository = playerRepository;
        this.achievementRepository = achievementRepository;
        this.authoritiesRepository = authoritiesRepository;

    }

    @Transactional(readOnly = true)
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);

        return players.stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlayerDTO getById(Integer id) {
        return playerRepository.findById(id)
                .map(PlayerDTO::new)
                .orElse(null);
    }

    @Transactional
    public PlayerDTO postPlayer(CreateEditPlayerDTO dto) {
        Player player = new Player();
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setAvatar(dto.getAvatar());
        player.setProfileInfo(dto.getProfileInfo());
        player.setIsOnline(dto.getIsOnline());

        // User
        Optional<Player> existingPlayer = playerRepository.findByUserUsername(dto.getUsername());
        if (existingPlayer.isPresent()) {
            throw new RuntimeException("Username already exists, it must be unique");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        if (dto.getAuthority() == null) {
            Authorities defaultAuthority = authoritiesRepository
                    .findByName("PLAYER")
                    .orElseThrow(() -> new RuntimeException("Default authority PLAYER not found"));
            user.setAuthority(defaultAuthority);
        } else {
            user.setAuthority(dto.getAuthority());
        }
        player.setUser(user);

        // Friends
        Set<Player> friends = new HashSet<>();
        if (dto.getFriends() != null) {
            for (String username : dto.getFriends()) {
                Player friend = playerRepository.findByUserUsername(username)
                        .orElseThrow(() -> new RuntimeException("Player " + username + " not found."));
                friends.add(friend);
            }

        }
        player.setFriends(friends);

        // Achievements
        List<Achievement> achievements = new ArrayList<>();
        if (dto.getAchievements() != null) {
            for (String name : dto.getAchievements()) {
                Achievement ach = achievementRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Achievement not found: " + name));
                achievements.add(ach);
            }
        }
        player.setAchievements(achievements);

        Player saved = playerRepository.save(player);
        return new PlayerDTO(saved);
    }

    @Transactional
    public PlayerDTO updatePlayer(Integer id, CreateEditPlayerDTO dto) {
        Player playerToUpdate = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerToUpdate.setFirstName(dto.getFirstName());
        playerToUpdate.setLastName(dto.getLastName());
        playerToUpdate.setAvatar(dto.getAvatar());
        playerToUpdate.setProfileInfo(dto.getProfileInfo());
        playerToUpdate.setIsOnline(dto.getIsOnline());

        // user
        User user = playerToUpdate.getUser();
        if (dto.getPassword() != null)
            user.setPassword(dto.getPassword());
        if (dto.getAuthority() != null)
            user.setAuthority(dto.getAuthority());
        playerToUpdate.setUser(user);

        // friends
        Set<Player> friends = new HashSet<>();
        if (dto.getFriends() != null) {
            for (String username : dto.getFriends()) {
                Player friend = playerRepository.findByUserUsername(username)
                        .orElseThrow(() -> new RuntimeException("Friend " + username + " not found."));
                friends.add(friend);
            }
        }
        playerToUpdate.setFriends(friends);

        // achievements
        List<Achievement> achievements = new ArrayList<>();
        if (dto.getAchievements() != null) {
            for (String name : dto.getAchievements()) {
                Achievement ach = achievementRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Achievement " + name + " not found."));
                achievements.add(ach);
            }
        }
        playerToUpdate.setAchievements(achievements);

        Player saved = playerRepository.save(playerToUpdate);
        return new PlayerDTO(saved);
    }

    @Transactional
    public void deletePlayerById(Integer id) {
        playerRepository.deleteById(id);
    }
}
