package es.us.dp1.l4_01_25_26.petris.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.l4_01_25_26.petris.game.dto.GameDTO;
import es.us.dp1.l4_01_25_26.petris.game.dto.PetriPlateDTO;
import es.us.dp1.l4_01_25_26.petris.game.dto.StartGameDTO;
import es.us.dp1.l4_01_25_26.petris.game.dto.TurnDTO;
import es.us.dp1.l4_01_25_26.petris.game.utils.PetriPlate;
import es.us.dp1.l4_01_25_26.petris.game.utils.PetriPlateService;
import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import es.us.dp1.l4_01_25_26.petris.game.utils.TurnManager;
import es.us.dp1.l4_01_25_26.petris.game.utils.TurnType;
import es.us.dp1.l4_01_25_26.petris.player.Player;
import es.us.dp1.l4_01_25_26.petris.player.PlayerService;
import es.us.dp1.l4_01_25_26.petris.user.UserService;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final PlayerService playerService;
    private final PetriPlateService petriPlateService;

    @Autowired
    public GameService(GameRepository gameRepository, UserService userService, PlayerService playerService,
            PetriPlateService petriPlateService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.playerService = playerService;
        this.petriPlateService = petriPlateService;

    }

    @Transactional(readOnly = true)
    public List<GameDTO> getAllGames() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        return games.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public GameDTO getByCode(String code) {
        Game game = gameRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        return convertToDTO(game);
    }

    @Transactional
    public Game createGame(Game game) {
        Game savedGame = gameRepository.save(game);
        petriPlateService.initializeBoard(game, 7);
        return savedGame;
    }

    @Transactional
    public Game updateGame(Game game) {
        Game gameToUpdate = gameRepository.findByCode(game.getCode()).get();
        if (gameToUpdate != null) {
            game.setId(gameToUpdate.getId());
            game.setCode(gameToUpdate.getCode());
            return gameRepository.save(game);
        }
        return null;
    }

    @Transactional
    public void deleteByCode(String code) {
        gameRepository.deleteByCode(code);
    }

    @Transactional
    public GameDTO startGame(StartGameDTO dto) {
        Game game = new Game();
        Integer bNum = dto.getBacteriaNumber() != null ? dto.getBacteriaNumber() : 20;
        Integer sNum = dto.getSarcinesNumber() != null ? dto.getSarcinesNumber() : 4;
        game.setInitialBacteriaNumber(bNum);
        game.setInitialSarcinesNumber(sNum);

        // 1st Generate code
        game.setCode(generateRandomCode(10));

        // 2nd Set Game Owner
        Player currentUser = playerService.getByUserUsername(
                userService.findCurrentUser().getUsername());
        game.setGameOwner(Objects.requireNonNull(currentUser, "Game Owner cannot be null"));

        // 3rd Set Players
        Player green = Objects.requireNonNull(playerService.getByIdEntity(dto.getGreenPlayerId()),
                "Green player cannot be null");
        Player purple = Objects.requireNonNull(playerService.getByIdEntity(dto.getPurplePlayerId()),
                "Purple player cannot be null");

        game.setGreenPlayer(green);
        game.setPurplePlayer(purple);

        // 4th Set Spectators
        List<Player> spectators = new ArrayList<>();

        if (dto.getSpectatorIds() != null) {
            for (Integer id : dto.getSpectatorIds()) {
                Player p = playerService.getByIdEntity(id);
                if (p == null) {
                    throw new IllegalArgumentException("Spectator with id " + id + " not found");
                }
                spectators.add(p);
            }
        }

        game.setSpectators(spectators);

        // 5th Set StartDate & EndDate
        LocalDateTime timeNow = LocalDateTime.now();
        game.setStartDate(timeNow);
        game.setEndDate(timeNow);

        // 6th Set PetriPlates or default 7
        int platesNumber = dto.getPetriPlatesNumber() != null ? dto.getPetriPlatesNumber() : 7;
        List<PetriPlate> plates = petriPlateService.initializeBoard(game, platesNumber);
        game.setPetriPlates(plates);

        // 8th Set TurnManager
        TurnManager tm = new TurnManager();
        tm.setTeam(dto.getTurnTeam() != null ? dto.getTurnTeam()
                : (Math.random() < 0.5 ? Team.GREEN : Team.PURPLE)); // Default random
        tm.setTurnCounter(0);
        tm.setTurnType(TurnType.CONTAMINATION);

        game.setTurn(tm);

        // 9th Set Contamination for Players or default 0
        game.setContaminationGreen(dto.getContaminationGreen() != null ? dto.getContaminationGreen() : 0);
        game.setContaminationPurple(dto.getContaminationPurple() != null ? dto.getContaminationPurple() : 0);

        gameRepository.save(game);
        return convertToDTO(game);
    }

    private GameDTO convertToDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setCode(game.getCode());
        dto.setStartDate(game.getStartDate());
        dto.setEndDate(game.getEndDate());
        dto.setGameOwnerId(game.getGameOwner().getId());
        dto.setGreenPlayerId(game.getGreenPlayer().getId());
        dto.setPurplePlayerId(game.getPurplePlayer().getId());
        dto.setSpectatorIds(game.getSpectators().stream().map(Player::getId).toList());
        dto.setBacteriaNumber(game.getInitialBacteriaNumber());
        dto.setSarcinesNumber(game.getInitialSarcinesNumber());

        // PetriPlates
        dto.setPetriPlatesNumber(game.getPetriPlates() != null ? game.getPetriPlates().size() : 0);
        if (game.getPetriPlates() != null) {
            dto.setPetriPlates(game.getPetriPlates().stream().map(this::convertPetriPlateToDTO).toList());
        }

        // TurnManager
        if (game.getTurn() != null) {
            TurnDTO turnDTO = new TurnDTO();
            turnDTO.setTurnCounter(game.getTurn().getTurnCounter());
            turnDTO.setTeam(game.getTurn().getTeam());
            turnDTO.setTurnType(game.getTurn().getTurnType());
            turnDTO.setRound(game.getTurn().getRound());
            dto.setTurn(turnDTO);
        }

        // Contamination
        dto.setContaminationGreen(game.getContaminationGreen() != null ? game.getContaminationGreen() : 0);
        dto.setContaminationPurple(game.getContaminationPurple() != null ? game.getContaminationPurple() : 0);

        return dto;
    }

    private PetriPlateDTO convertPetriPlateToDTO(PetriPlate plate) {
        PetriPlateDTO dto = new PetriPlateDTO();
        dto.setId(plate.getId());
        dto.setGreenBacteria(plate.getGreenBacteria());
        dto.setGreenSarcines(plate.getGreenSarcines());
        dto.setPurpleBacteria(plate.getPurpleBacteria());
        dto.setPurpleSarcines(plate.getPurpleSarcines());
        dto.setPosition(plate.getPosition());
        return dto;
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

    @Transactional
    public GameDTO playTurn(String code) {

        // 1. Cargar la partida
        Game game = gameRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // 2. Recuperar el TurnManager
        TurnManager tm = game.getTurn();
        if (tm == null) {
            throw new IllegalStateException("TurnManager not initialized for this game");
        }

        // 3. Avanzar un turno usando TU lógica
        tm.advance(); // aquí usas exactamente tu TurnManager actual

        // 4. Según el tipo de turno, llamar a PetriPlateService
        switch (tm.getTurnType()) {
            case MOVEMENT:
                // petriPlateService.applyMovement(game, tm.getTeam(), ...);
                break;
            case MOLECULAR_FISSION:
                // petriPlateService.applyFission(game, tm.getTeam(), ...);
                break;
            case CONTAMINATION:
                // petriPlateService.applyContamination(game, tm.getTeam(), ...);
                break;
        }

        // 5. Guardar estado
        gameRepository.save(game);

        // 6. Devolver estado actualizado
        return convertToDTO(game);
    }

}