package es.us.dp1.l4_01_25_26.petris.game.utils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnManager {

    @NotNull(message = "Turn counter cannot be null")
    @PositiveOrZero(message = "Turn counter must be positive or zero")
    private Integer turnCounter = 0;

    @NotNull(message = "Turn cannot be null")
    private Team team;

    @NotNull(message = "Turn type cannot be null")
    private TurnType turnType=TurnType.CONTAMINATION;

    private Integer round = 0;

    public static void nextTurn(TurnManager turnManager){
        turnManager.setTurnCounter(turnManager.getTurnCounter() + 1);
    }

    public static void nextRound(TurnManager turnManager){
        turnManager.setRound(turnManager.getRound()+1);
    }

    public static TurnManager teamChange(TurnManager turnManager){
        TurnManager turnManagerCopy = turnManager;
        if(turnManagerCopy.getRound()%2==0 && turnManagerCopy.getTurnCounter()%2==1){
            turnManagerCopy.setTeam(Team.PURPLE);
        }else{
            turnManagerCopy.setTeam(Team.GREEN);
        }
        return turnManagerCopy;
    }


    public static void turnLogic(TurnManager turnManager){
        TurnManager newTurn = teamChange(turnManager);
        Integer turnNumber = newTurn.getTurnCounter(); 
        if((turnNumber)%3==0){
            //fision
            turnManager.setTurnType(TurnType.MOLECULAR_FISSION);
            nextTurn(turnManager);
        }else if(turnNumber%10==0){
            //contaminacion
            turnManager.setTurnType(TurnType.CONTAMINATION);
            nextRound(turnManager);
            nextTurn(turnManager);
        }else{
            turnManager.setTurnType(TurnType.MOVEMENT);
            nextTurn(turnManager);
        }

        //purple turns: 1,4,7,  12,15,18,  21,24,27,  32,35,38
        //green turns: 2,5,8,  11,14,17,  22,25,28,  31,34,37
    }
}
