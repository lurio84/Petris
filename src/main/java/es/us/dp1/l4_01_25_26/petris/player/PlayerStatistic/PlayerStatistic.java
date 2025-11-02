package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.Check;

import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import es.us.dp1.l4_01_25_26.petris.player.Player;

@Getter
@Setter
@Entity
@Table(name = "player_statistics")
public class PlayerStatistic extends BaseEntity {

    @PositiveOrZero(message = "Friends number must be zero or positive")
    @Max(value = 150, message = "Friends number can't be higher 150")
    @Column(nullable = false)
    private Integer friends = 0;

    @NotNull(message = "First connection date can't be null")
    @Column(nullable = false)
    private LocalDateTime firstConnection = LocalDateTime.now();;

    @NotNull(message = "Last connection date can't be null")
    @Column(nullable = false)
    private LocalDateTime lastConnection = LocalDateTime.now();;

    @NotNull(message = "First game played date can't be null")
    @Column(nullable = false)
    private LocalDateTime firstGamePlayed = LocalDateTime.now();;

    @NotNull(message = "Last game played date can't be null")
    @Column(nullable = false)
    private LocalDateTime lastGamePlayed = LocalDateTime.now();;

    @NotNull(message = "Games played can't be null")
    @PositiveOrZero(message = "Games played must be zero or positive")
    @Column(nullable = false)
    private Integer gamesPlayed = 0;

    @NotNull(message = "Games won can't be null")
    @PositiveOrZero(message = "Games won must be zero or positive")
    @Column(nullable = false)
    private Integer gamesWon = 0;

    @NotNull(message = "Games played as green can't be null")
    @PositiveOrZero(message = "Games played as green must be zero or positive")
    @Column(nullable = false)
    private Integer gamesAsGreen = 0;

    @NotNull(message = "Games played as purple can't be null")
    @PositiveOrZero(message = "Games played as purple must be zero or positive")
    @Column(nullable = false)
    private Integer gamesAsPurple = 0;

    @NotNull(message = "Favourite team can't be null. It has to be Green or Purple")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Team favouriteTeam=Team.GREEN;

    @NotNull(message="Victories played as green can't be null")
    @PositiveOrZero(message="Victories played as green must be zero or positive")
    @Column(nullable=false)
    private Integer victoriesAsGreen = 0;

    @NotNull(message = "Victories played as purple can't be null")
    @PositiveOrZero(message = "Victories played as purple must be zero or positive")
    @Column(nullable = false)
    private Integer victoriesAsPurple = 0;

    @NotNull(message = "Max Bactery played as green can't be null")
    @PositiveOrZero(message = "Max Bactery played as green must be zero or positive")
    @Column(nullable = false)
    private Integer maxBacteryPlayedAsGreen = 0;

    @NotNull(message = "Max Bactery played as green can't be null")
    @PositiveOrZero(message = "Max Bactery played as green must be zero or positive")
    @Column(nullable = false)
    private Integer maxBacteryPlayedAsPurple = 0;

    @NotNull(message = "Max Sarcine played as purple can't be null")
    @PositiveOrZero(message = "Max Sarcine played as purple must be zero or positive")
    @Column(nullable = false)
    private Integer maxSarcinePlayedAsGreen = 0;

    @NotNull(message = "Max Sarcine played as purple can't be null")
    @PositiveOrZero(message = "Max Sarcine played as purple must be zero or positive")
    @Column(nullable = false)
    private Integer maxSarcinePlayedAsPurple = 0;

    @NotNull(message = "Max turns played as green can't be null")
    @PositiveOrZero(message = "Max turns played as green must be zero or positive")
    @Column(nullable = false)
    private Integer maxTurnsPlayedAsGreen = 0;

    @NotNull(message = "Max turns played as purple can't be null")
    @PositiveOrZero(message = "Max turns played as purple must be zero or positive")
    @Column(nullable = false)
    private Integer maxTurnsPlayedAsPurple = 0;

    
    @NotNull(message = "Player asociated can't be null")
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Player player;
 

}
