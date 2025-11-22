package es.us.dp1.l4_01_25_26.petris.game.dto;

import java.util.List;

import es.us.dp1.l4_01_25_26.petris.game.utils.Microorganism;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetriPlateDTO {

    private List<Microorganism> greenBacteria;
    private List<Microorganism> greenSarcines;
    private List<Microorganism> purpleBacteria;
    private List<Microorganism> purpleSarcines;
    private Integer position;
    private List<Integer> adjacentTiles;
}
