package es.us.dp1.l4_01_25_26.petris.game;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

public class PetriPlate {
    
    @NotNull(message = "The list of green bacteria must not be null")
    private List<MicroOrganismType> greenBacteria = new ArrayList<>();

    @NotNull(message = "The list of green sarcines must not be null")
    private List<MicroOrganismType> greenSarcines = new ArrayList<>();

    @NotNull(message = "The list of purple bacteria must not be null")
    private List<MicroOrganismType> purpleBacteria = new ArrayList<>();

    @NotNull(message = "The list of purple sarcines must not be null")
    private List<MicroOrganismType> purpleSarcines = new ArrayList<>();

    //#################### OTHER RELATIONS ############################

    // @ManyToMany(mappedBy = "petriPlates")
    // private List<Game> games = new ArrayList<>();



}
