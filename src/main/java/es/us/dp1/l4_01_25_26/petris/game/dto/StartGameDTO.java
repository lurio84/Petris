package es.us.dp1.l4_01_25_26.petris.game.dto;

import java.time.LocalDateTime;
import java.util.List;

import es.us.dp1.l4_01_25_26.petris.game.utils.Microorganism;
import es.us.dp1.l4_01_25_26.petris.game.utils.PetriPlate;
import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartGameDTO {
    @NotNull
    private Integer greenPlayerId;

    @NotNull
    private Integer purplePlayerId;

    private List<Integer> spectatorIds;

    private Team turnTeam;

    @Positive
    private Integer petriPlatesNumber;
    
    @Positive
    private Integer bacteriaNumber;
    
    @Positive
    private Integer sarcinesNumber;

    @PositiveOrZero
    private Integer contaminationGreen;
    
    @PositiveOrZero
    private Integer contaminationPurple;

}
