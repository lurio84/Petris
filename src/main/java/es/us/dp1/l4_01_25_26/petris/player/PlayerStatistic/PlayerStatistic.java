package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.Check;

import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "player_statistics")
//@Check(constraints = "friends >= 0 AND friends <= 150 AND gamesPlayed >= 0 AND gamesWon >= 0 AND gamesAsGreen >= 0 AND gamesAsPurple >= 0 AND victoriesAsGreen >= 0 AND victoriesAsPurple >= 0 AND maxBacteryPlayedAsGreen >= 0 AND maxSarcinePlayedAsPurple >= 0 AND maxTurnsPlayedAsGreen >= 0 AND maxTurnsPlayedAsPurple >= 0") // No compatible con h2 :'( 
public class PlayerStatistic extends BaseEntity {

    @PositiveOrZero(message = "Friends number must be zero or positive")
    @Max(value = 150, message = "Friends number can't be higher 150")
    @Column(nullable = false)
    private Integer friends = 0;

    @NotNull(message = "First connection date can't be null")
    @Column(nullable = false)
    private LocalDateTime firstConnection;

    @NotNull(message = "Last connection date can't be null")
    @Column(nullable = false)
    private LocalDateTime lastConnection;

    @NotNull(message = "First game played date can't be null")
    @Column(nullable = false)
    private LocalDateTime firstGamePlayed;

    @NotNull(message = "Last game played date can't be null")
    @Column(nullable = false)
    private LocalDateTime lastGamePlayed;

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
    private Team favouriteTeam;

    @NotNull(message = "Victories played as green can't be null")
    @PositiveOrZero(message = "Victories played as green must be zero or positive")
    @Column(nullable = false)
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

    public static PlayerStatistic empty() {
        PlayerStatistic ps = new PlayerStatistic();
        ps.setFriends(0);
        ps.setGamesAsGreen(0);
        ps.setGamesAsPurple(0);
        ps.setGamesPlayed(0);
        ps.setGamesWon(0);
        ps.setVictoriesAsGreen(0);
        ps.setVictoriesAsPurple(0);
        ps.setMaxBacteryPlayedAsGreen(0);
        ps.setMaxBacteryPlayedAsPurple(0);
        ps.setMaxSarcinePlayedAsGreen(0);
        ps.setMaxSarcinePlayedAsPurple(0);
        ps.setMaxTurnsPlayedAsGreen(0);
        ps.setMaxTurnsPlayedAsPurple(0);
        ps.setFavouriteTeam(Team.GREEN);
        ps.setFirstConnection(LocalDateTime.now());
        ps.setLastConnection(LocalDateTime.now());
        ps.setFirstGamePlayed(LocalDateTime.now());
        ps.setLastGamePlayed(LocalDateTime.now());

        return ps;
    }

}
