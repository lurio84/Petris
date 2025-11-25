package es.us.dp1.l4_01_25_26.petris.game.GameStatistics;

import es.us.dp1.l4_01_25_26.petris.game.Game;
import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GameStatistics extends BaseEntity {

    @PositiveOrZero
    @NotNull
    private Integer turnsPlayed = 0;

    @PositiveOrZero
    @NotNull
    private Integer totalBacteries = 0;

    @PositiveOrZero
    @NotNull
    private Integer totalSarcines = 0;

    @NotBlank
    @Size(min = 10, max = 60)
    private String winner;

    @PositiveOrZero
    @NotNull
    private Integer greenPlayerBacteries = 0;

    @PositiveOrZero
    @NotNull
    private Integer greenPlayerSarcines = 0;

    @PositiveOrZero
    @NotNull
    private Integer purplePlayerBacteries = 0;

    @PositiveOrZero
    @NotNull
    private Integer purplePlayerSarcines = 0;

    @OneToOne(optional = false)
    @JoinColumn(name = "game_id", nullable = false, unique = true)
    private Game game;
}