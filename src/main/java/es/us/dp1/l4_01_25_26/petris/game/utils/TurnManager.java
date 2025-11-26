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
    private Integer turnCounter = 1;

    @NotNull(message = "Turn cannot be null")
    private Team team;

    @NotNull(message = "Turn type cannot be null")
    private TurnType turnType=TurnType.MOVEMENT;

    private Integer round = 1;

    public static void nextTurn(TurnManager turnManager){
        turnManager.setTurnCounter(turnManager.getTurnCounter() + 1);
    }

    public static void nextRound(TurnManager turnManager){
        turnManager.setRound(turnManager.getRound()+1);
    }

    public static TurnManager teamChange(TurnManager turnManager){
        TurnManager turnManagerCopy = turnManager;
        if(turnManagerCopy.getRound()%2==1){
            if((turnManagerCopy.getTurnCounter()+1)%3==1)
                turnManagerCopy.setTeam(Team.PURPLE);
            else if((turnManagerCopy.getTurnCounter()+1)%3==2)
                turnManagerCopy.setTeam(Team.GREEN);
        }else{
            if((turnManagerCopy.getTurnCounter()+1)%3==1)
                turnManagerCopy.setTeam(Team.PURPLE);
            else if((turnManagerCopy.getTurnCounter()+1)%3==2)
                turnManagerCopy.setTeam(Team.GREEN);
        }
        return turnManagerCopy;
    }


    public static void turnLogic(TurnManager turnManager){
        TurnManager newTurn = teamChange(turnManager);
        Integer turnNumber = newTurn.getTurnCounter(); 
        if((turnNumber+1)%3==0){
            //fision
            newTurn.setTurnType(TurnType.MOLECULAR_FISSION);
            nextTurn(newTurn);
            teamChange(newTurn);
        }else if((turnNumber+1)%10==0){
            //contaminacion
            newTurn.setTurnType(TurnType.CONTAMINATION);
            newTurn.setTurnCounter(0);
            nextRound(newTurn);
            //nextTurn(newTurn);
            teamChange(newTurn);
        }else{
            newTurn.setTurnType(TurnType.MOVEMENT);
            teamChange(newTurn);
            nextTurn(newTurn);
            System.out.println("Siguiente turno normal");
        }

        //purple turns: 1,4,7,  12,15,18,  21,24,27,  32,35,38
        //green turns: 2,5,8,  11,14,17,  22,25,28,  31,34,37
    }

    public static void main(String[] args) {
        TurnManager turnManager = new TurnManager();
        turnManager.setTeam(Team.PURPLE);
        for(int i =0 ; i<39;i++){
            System.out.println("Ronda: " + turnManager.getRound());
            System.out.println(turnManager.getTeam());
            System.out.println("Tipo: " + turnManager.getTurnType());
            System.out.println("Turno: " + turnManager.getTurnCounter());
            System.out.println("---------------------------------");
            turnLogic(turnManager);
        }

    }
}

