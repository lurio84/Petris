package es.us.dp1.l4_01_25_26.petris.game.utils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class TurnManager {

    @NotNull(message = "Turn counter cannot be null")
    @PositiveOrZero(message = "Turn counter must be positive or zero")
    private Integer turnCounter = 0;

    @NotNull(message = "Turn cannot be null")
    private Team team;

    @NotNull(message = "Turn type cannot be null")
    private TurnType turnType=TurnType.CONTAMINATION;

    
}
