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

    private Integer initialBacteriaNumber;
    private Integer initialSarcinesNumber;

    // ################ GAME OBJECTS IN MEMORY (not persisted in db)
    // ################
    @Transient
    private List<Microorganism> microorganisms = new ArrayList<>();

    @Transient
    private TurnManager turn = new TurnManager();

    @Transient
    private List<Player> spectators = new ArrayList<>();

    @Transient
    private Integer contaminationGreen = 0;

    @Transient
    private Integer contaminationPurple = 0;

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

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetriPlate> petriPlates = new ArrayList<>();

}
