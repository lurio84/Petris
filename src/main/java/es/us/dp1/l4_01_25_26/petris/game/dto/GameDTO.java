package es.us.dp1.l4_01_25_26.petris.game.dto;

import java.time.LocalDateTime;
import java.util.List;

import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {
    private Integer id;
    private String code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer gameOwnerId;
    private Integer greenPlayerId;
    private Integer purplePlayerId;
    private List<Integer> spectatorIds;
    private Integer petriPlatesNumber;
    private Integer bacteriaNumber;
    private Integer sarcinesNumber;
    private Team turnTeam;
    private Integer contaminationGreen;
    private Integer contaminationPurple;
}
