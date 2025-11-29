package es.us.dp1.l4_01_25_26.petris.game.dto;

import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import es.us.dp1.l4_01_25_26.petris.game.utils.TurnType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnDTO {
    private Integer turnCounter;
    private Team team;
    private TurnType turnType;
    private Integer round;
}
