package es.us.dp1.l4_01_25_26.petris.game.utils;

import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PetriPlate extends BaseEntity {

    @NotNull(message = "The list of green bacteria must not be null")
    private int greenBacteria = 0;

    @NotNull(message = "The list of green sarcines must not be null")
    private int greenSarcines = 0;

    @NotNull(message = "The list of purple bacteria must not be null")
    private int purpleBacteria = 0;

    @NotNull(message = "The list of purple sarcines must not be null")
    private int purpleSarcines = 0;

    @NotNull(message = "The position of the tile must not be null")
    @Min(1)
    @Max(7)
    private Integer position;
}
