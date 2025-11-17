package es.us.dp1.l4_01_25_26.petris.game.utils;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Microorganism {
    
    @NotNull(message = "Team cannot be null")
    private Team team;

    @NotNull(message = "Type cannot be null")
    private MicroOrganismType type;

}
