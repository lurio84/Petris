package es.us.dp1.l4_01_25_26.petris.game.utils;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class TurnManager {

    // Turno absoluto: 1..40 (4 rondas x 10 turnos)
    @NotNull(message = "Turn counter cannot be null")
    @PositiveOrZero(message = "Turn counter must be positive or zero")
    private Integer turnCounter = 1;

    @NotNull(message = "Turn cannot be null")
    private Team team = Team.PURPLE; // siempre empieza Purple

    @NotNull(message = "Turn type cannot be null")
    private TurnType turnType = TurnType.MOVEMENT;

    @NotNull
    private Integer round = 1;

    private static final int MAX_ROUNDS = 4;
    private static final int TURNS_PER_ROUND = 10;

    /**
     * Calcula el turno actual (team + tipo + ronda) a partir de turnCounter
     * y deja preparado el siguiente contador (turnCounter++).
     */
    public void advance() {
        int absTurn = this.turnCounter;

        // Límite duro: 4 rondas x 10 turnos = 40
        if (absTurn > MAX_ROUNDS * TURNS_PER_ROUND) {
            return;
        }

        // Ronda actual (1..4)
        int currentRound = (absTurn - 1) / TURNS_PER_ROUND + 1;
        this.round = currentRound;

        // Posición dentro de la ronda (0..9)
        int pos = (absTurn - 1) % TURNS_PER_ROUND;

        // Turno 10 de cada ronda → CONTAMINATION
        if (pos == 9) {
            this.turnType = TurnType.CONTAMINATION;

            // Decidimos qué equipo empezará la siguiente ronda:
            // - si la ronda actual es impar (1,3) → next empieza Green
            // - si es par (2,4) → next empieza Purple
            boolean oddRound = (currentRound % 2 == 1);
            this.team = oddRound ? Team.GREEN : Team.PURPLE;

        } else {
            int step = pos % 3; // 0,1,2 dentro de cada bloque de 3
            boolean oddRound = (currentRound % 2 == 1);

            if (step == 2) {
                // Tercer turno del bloque: FISION
                this.turnType = TurnType.MOLECULAR_FISSION;
                // Puedes dejar team tal cual o asociarlo al último que jugó;
                // aquí lo dejo sin cambiar para que el tipo sea lo importante.
            } else {
                // Movimientos normales
                this.turnType = TurnType.MOVEMENT;

                if (oddRound) {
                    // Rondas 1 y 3 → Purple luego Green
                    this.team = (step == 0) ? Team.PURPLE : Team.GREEN;
                } else {
                    // Rondas 2 y 4 → Green luego Purple
                    this.team = (step == 0) ? Team.GREEN : Team.PURPLE;
                }
            }
        }

        // Dejamos preparado el siguiente turno absoluto
        this.turnCounter = absTurn + 1;
    }

    // Pequeño main de prueba
    public static void main(String[] args) {
        TurnManager turnManager = new TurnManager();

        // 40 acciones máximo (4 rondas x 10 turnos)
        for (int i = 0; i < 40; i++) {
            turnManager.advance();
            System.out.println("Turno abs: " + (i + 1));
            System.out.println("Ronda: " + turnManager.getRound());
            System.out.println("Team: " + turnManager.getTeam());
            System.out.println("Tipo: " + turnManager.getTurnType());
            System.out.println("---------------------------------");
        }
    }
}
