package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetriPlate {
    
    private Integer id;

    public static PetriPlate of(Integer id){
        PetriPlate plate = new PetriPlate();
        plate.setId(id);
        return plate;
    }

    public static final Map<Integer, List<Integer>> adjacentMap = Map.of(
        0,List.of(1,5,6),
        1,List.of(0,2,6),
        2,List.of(1,3,6),
        3,List.of(2,4,6),
        4,List.of(3,5,6),
        5,List.of(4,0,6),
        6,List.of(0,1,2,3,4,5)
    );
    

    @NotNull(message = "The list of green bacteria must not be null")
    private List<Microorganism> greenBacteria = new ArrayList<>();

    @NotNull(message = "The list of green sarcines must not be null")
    private List<Microorganism> greenSarcines = new ArrayList<>();

    @NotNull(message = "The list of purple bacteria must not be null")
    private List<Microorganism> purpleBacteria = new ArrayList<>();

    @NotNull(message = "The list of purple sarcines must not be null")
    private List<Microorganism> purpleSarcines = new ArrayList<>();


    public static boolean checkAdjacent(PetriPlate plateFrom, PetriPlate plateTo){
        boolean check = PetriPlate.adjacentMap.get(plateFrom.id).contains(plateTo.id);
        return check;
    }

    public static boolean checkSarcineConversion(PetriPlate plate){
        boolean greenConversion = plate.greenBacteria.size() == 5;
        boolean purpleConversion = plate.purpleBacteria.size() == 5;
        return sarcineConversion(greenConversion,purpleConversion, plate);
    }

    public static boolean sarcineConversion(boolean greenConversion, boolean purpleConversion, PetriPlate plate){
        boolean flag = false;
        if(greenConversion){
            Microorganism greenSarcine = new Microorganism(Team.GREEN, MicroOrganismType.SARCINE);
            plate.setGreenBacteria(List.of());
            plate.setGreenSarcines(List.of(greenSarcine));
            flag = true;
        }
        else if(purpleConversion){
            Microorganism purpleSarcine = new Microorganism(Team.PURPLE, MicroOrganismType.SARCINE);
            plate.setGreenBacteria(List.of());
            plate.setGreenSarcines(List.of(purpleSarcine));
            flag = true;
        }

        return flag;
    }

public static void main(String[] args) {
        System.out.println(checkAdjacent(PetriPlate.of(1), PetriPlate.of(2)));
        System.out.println(checkAdjacent(PetriPlate.of(1), PetriPlate.of(6)));
        System.out.println(checkAdjacent(PetriPlate.of(1), PetriPlate.of(3)));
        System.out.println(checkAdjacent(PetriPlate.of(0), PetriPlate.of(5)));
    }
}
