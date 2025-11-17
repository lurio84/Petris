package es.us.dp1.l4_01_25_26.petris.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.us.dp1.l4_01_25_26.petris.achievement.Achievement;
import es.us.dp1.l4_01_25_26.petris.model.Person;
import es.us.dp1.l4_01_25_26.petris.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends Person {

    @NotBlank(message = "Avatar can't be blank")
    @Size(min = 5, max = 60, message = "Avatar must be between 5 and 60 characters")
    @Column(nullable = false, length = 60)
    private String avatar = "defaultAvatar.png";

    @Size(max = 500, message = "Profile information can't exceed 500 characters")
    @Column(length = 500)
    private String profileInfo = "";

    @NotNull(message = "Online status can't be null")
    @Column(nullable = false)
    private Boolean isOnline = false;

    @NotNull(message = "User can't be null")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // Add orphanRemoval to handle cascading deletes
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false) 
    private User user;

    @NotNull(message = "Friends set can't be null")
    @ManyToMany
    @JoinTable(name = "players_friends", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<Player> friends = new HashSet<>();

    @NotNull(message = "Achievements list can't be null")
    @ManyToMany() // Only cascade on create and update
    private List<Achievement> achievements = new ArrayList<>();;

}
