package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.ArrayList;
import java.util.List;

import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "petri_plates")
public class PetriPlate extends BaseEntity{
    
    @NotNull(message = "The list of green bacteria must not be null")
    private Integer greenBacteria;

    @NotNull(message = "The list of green sarcines must not be null")
    private Integer greenSarcines;

    @NotNull(message = "The list of purple bacteria must not be null")
    private Integer purpleBacteria;

    @NotNull(message = "The list of purple sarcines must not be null")
    private Integer purpleSarcines;

    @NotNull(message = "The position of the tile must not be null")
    @Min(1)
    @Max(7)
    private Integer position;


    @ElementCollection
    @CollectionTable(name = "petri_plate_adjacent_tiles", joinColumns = @JoinColumn(name = "petri_plate_id"))
    @Column(name = "tile_id")
    private List<Integer> adjacentTiles;

}
