package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.dto;

import java.time.LocalDateTime;

import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.PlayerStatistic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStatisticDTO {
    private Integer friends;
    private LocalDateTime firstConnection;
    private LocalDateTime lastConnection;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private Integer gamesAsGreen;
    private Integer gamesAsPurple;
    private Team favouriteTeam;
    private Integer victoriesAsGreen;
    private Integer victoriesAsPurple;
    private Integer maxBacteryPlayedAsGreen;
    private Integer maxBacteryPlayedAsPurple;
    private Integer maxSarcinePlayedAsGreen;
    private Integer maxSarcinePlayedAsPurple;
    private Integer maxTurnsPlayedAsGreen;
    private Integer maxTurnsPlayedAsPurple;
    private String username;

    public PlayerStatisticDTO(PlayerStatistic ps) {
        this.friends = ps.getFriends();
        this.firstConnection = ps.getFirstConnection();
        this.lastConnection = ps.getLastConnection();
        this.gamesPlayed = ps.getGamesPlayed();
        this.gamesWon = ps.getGamesWon();
        this.gamesAsGreen = ps.getGamesAsGreen();
        this.gamesAsPurple = ps.getGamesAsPurple();
        this.favouriteTeam = ps.getFavouriteTeam();
        this.victoriesAsGreen = ps.getVictoriesAsGreen();
        this.victoriesAsPurple = ps.getVictoriesAsPurple();
        this.maxBacteryPlayedAsGreen = ps.getMaxBacteryPlayedAsGreen();
        this.maxBacteryPlayedAsPurple = ps.getMaxBacteryPlayedAsPurple();
        this.maxSarcinePlayedAsGreen = ps.getMaxSarcinePlayedAsGreen();
        this.maxSarcinePlayedAsPurple = ps.getMaxSarcinePlayedAsPurple();
        this.maxTurnsPlayedAsGreen = ps.getMaxTurnsPlayedAsGreen();
        this.maxTurnsPlayedAsPurple = ps.getMaxTurnsPlayedAsPurple();
        this.username = ps.getPlayer().getUser().getUsername();
    }
}
