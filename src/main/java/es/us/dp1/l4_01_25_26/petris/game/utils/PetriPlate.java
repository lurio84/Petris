package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetriPlate {
    
    @NotNull(message = "The list of green bacteria must not be null")
    private List<Microorganism> greenBacteria = new ArrayList<>();

    @NotNull(message = "The list of green sarcines must not be null")
    private List<Microorganism> greenSarcines = new ArrayList<>();

    @NotNull(message = "The list of purple bacteria must not be null")
    private List<Microorganism> purpleBacteria = new ArrayList<>();

    @NotNull(message = "The list of purple sarcines must not be null")
    private List<Microorganism> purpleSarcines = new ArrayList<>();

    @NotNull(message = "The position of the tile must not be null")
    @Min(1)
    @Max(7)
    private Integer position;

    @NotNull
    private List<Integer> adjacentTiles;

}
