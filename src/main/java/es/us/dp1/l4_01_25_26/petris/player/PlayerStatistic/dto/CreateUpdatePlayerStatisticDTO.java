package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.dto;

import java.time.LocalDateTime;

import es.us.dp1.l4_01_25_26.petris.game.utils.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdatePlayerStatisticDTO {

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
}
