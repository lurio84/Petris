package es.us.dp1.l4_01_25_26.petris.game;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import es.us.dp1.l4_01_25_26.petris.game.utils.MicroOrganismType;
import es.us.dp1.l4_01_25_26.petris.game.utils.Microorganism;
import es.us.dp1.l4_01_25_26.petris.game.utils.PetriPlate;
import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import es.us.dp1.l4_01_25_26.petris.game.utils.TurnManager;
import es.us.dp1.l4_01_25_26.petris.model.BaseEntity;
import es.us.dp1.l4_01_25_26.petris.player.Player;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @NotNull(message = "Game code can't be null")
    @Size(min = 10, max = 10, message = "Game code must have 10 characters")
    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @NotNull(message = "Start date can't be null")
    @Column(nullable = false)
    private LocalDateTime startDate;

    @NotNull(message = "End date can't be null")
    @Column(nullable = false)
    private LocalDateTime endDate;

    // ################ GAME OBJECTS IN MEMORY (not persisted in db) ################
    @Transient
    private List<PetriPlate> petriPlates = new ArrayList<>();

    @Transient
    private List<Microorganism> microorganisms = new ArrayList<>();

    @Transient
    private TurnManager turn = new TurnManager();

    @Transient
    private List<Player> spectators = new ArrayList<>();

    // ################ RELATIONS ################
    @NotNull(message = "Game owner can't be null")
    @ManyToOne
    @JoinColumn(name = "game_owner", nullable = false)
    private Player gameOwner;

    @NotNull(message = "Green player can't be null")
    @ManyToOne
    @JoinColumn(name = "green_player", nullable = false)
    private Player greenPlayer;

    @NotNull(message = "Purple player can't be null")
    @ManyToOne
    @JoinColumn(name = "purple_player", nullable = false)
    private Player purplePlayer;

    public Game() {
        this.code = generateRandomCode(10);
        this.startDate = LocalDateTime.now();

        // 4 PetriPlates default
        this.petriPlates = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.petriPlates.add(new PetriPlate());
        }

        // Microorganisms: 20 bacterium & 4 sarcine each team
        this.microorganisms = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Microorganism m1 = new Microorganism();
            m1.setTeam(Team.GREEN);
            m1.setType(MicroOrganismType.BACTERIUM);
            this.microorganisms.add(m1);

            Microorganism m2 = new Microorganism();
            m2.setTeam(Team.PURPLE);
            m2.setType(MicroOrganismType.BACTERIUM);
            this.microorganisms.add(m2);
        }
        for (int i = 0; i < 4; i++) {
            Microorganism m1 = new Microorganism();
            m1.setTeam(Team.GREEN);
            m1.setType(MicroOrganismType.SARCINE);
            this.microorganisms.add(m1);

            Microorganism m2 = new Microorganism();
            m2.setTeam(Team.PURPLE);
            m2.setType(MicroOrganismType.SARCINE);
            this.microorganisms.add(m2);
        }

        // TurnManager
        this.turn = new TurnManager();

        // Spectators default
        this.spectators = new ArrayList<>();
    }

    private static String generateRandomCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String code = "";
        for (int i = 0; i < length; i++) {
            int idx = (int) (Math.random() * chars.length());
            code += chars.charAt(idx);
        }
        return code;
    }
}
