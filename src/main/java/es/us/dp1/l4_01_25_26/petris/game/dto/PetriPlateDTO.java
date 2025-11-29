package es.us.dp1.l4_01_25_26.petris.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetriPlateDTO {
    private Integer id;
    private int greenBacteria;
    private int greenSarcines;
    private int purpleBacteria;
    private int purpleSarcines;
    private Integer position;
}
