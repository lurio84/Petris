package es.us.dp1.l4_01_25_26.petris.player.dto;

import java.util.List;
import java.util.stream.Collectors;

import es.us.dp1.l4_01_25_26.petris.player.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String profileInfo;
    private Boolean isOnline;
    private List<String> friends;       // usernames
    private List<String> achievements;  // achievement names

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.avatar = player.getAvatar();
        this.profileInfo = player.getProfileInfo();
        this.isOnline = player.getIsOnline();
        this.username = player.getUser().getUsername();
        
        this.friends = player.getFriends().stream()
                             .map(f -> f.getUser().getUsername())
                             .collect(Collectors.toList());
        this.achievements = player.getAchievements().stream()
                                  .map(a -> a.getName())
                                  .collect(Collectors.toList());
    }
}
