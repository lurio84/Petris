package es.us.dp1.l4_01_25_26.petris.statistic;

import java.time.LocalDateTime;

import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player_statistics")
public class PlayerStadistic extends BaseEntity {
    
    @Min(0)
    @Max(150)
    private Integer friends;
    @NotNull
    private LocalDateTime firstConnection;
    @NotNull
    private LocalDateTime lastConnection;
    @NotNull
    private LocalDateTime firstGamePlayed;
    @NotNull
    private LocalDateTime lastGamePlayed;

    @NotNull
    @Positive
    private Integer gamesPlayed;

    @NotNull
    @Positive
    private Integer gamesWon;
    
    @NotNull
    @Positive
    private Integer gamesAsGreen;
    
    @NotNull
    @Positive
    private Integer gamesAsPurple;
    
    @NotNull
    private Team favouriteTeam;
    
    @NotNull
    @Positive
    private Integer victoriesAsGreen;
    
    @NotNull
    @Positive
    private Integer victoriesAsInteger;
    
    @NotNull
    @Positive
    private Integer maxBacteryPlayedAsGreen;
    
    @NotNull
    @Positive
    private Integer maxBacteryPlayedAsPurple;
    
    @NotNull
    @Positive
    private Integer maxSarcinePlayedAsGreen;
    
    @NotNull
    @Positive
    private Integer maxSarcinePlayedAsPurple;
    
    @NotNull
    @Positive
    private Integer maxTurnsPlayedAsGreen;
    
    @NotNull
    @Positive
    private Integer maxTurnsPlayedAsPurple;


}
