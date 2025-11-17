package es.us.dp1.l4_01_25_26.petris.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.l4_01_25_26.petris.game.dto.GameDTO;
import es.us.dp1.l4_01_25_26.petris.game.dto.StartGameDTO;
import es.us.dp1.l4_01_25_26.petris.game.utils.MicroOrganismType;
import es.us.dp1.l4_01_25_26.petris.game.utils.Microorganism;
import es.us.dp1.l4_01_25_26.petris.game.utils.PetriPlate;
import es.us.dp1.l4_01_25_26.petris.game.utils.Team;
import es.us.dp1.l4_01_25_26.petris.game.utils.TurnManager;
import es.us.dp1.l4_01_25_26.petris.game.utils.TurnType;
import es.us.dp1.l4_01_25_26.petris.player.Player;
import es.us.dp1.l4_01_25_26.petris.player.PlayerService;
import es.us.dp1.l4_01_25_26.petris.player.dto.PlayerDTO;
import es.us.dp1.l4_01_25_26.petris.user.User;
import es.us.dp1.l4_01_25_26.petris.user.UserService;
import jakarta.validation.Valid;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final PlayerService playerService;

    @Autowired
    public GameService(GameRepository gameRepository, UserService userService, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.playerService = playerService;

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
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateGame(Game game) {
        Game gameToUpdate = gameRepository.findByCode(game.getCode());
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
        List<PetriPlate> plates = new ArrayList<>();
        for (int i = 0; i < platesNumber; i++) {
            plates.add(new PetriPlate());
        }
        game.setPetriPlates(plates);

        Integer bNum = dto.getBacteriaNumber() != null ? dto.getBacteriaNumber() : 20;
        Integer sNum = dto.getSarcinesNumber() != null ? dto.getSarcinesNumber() : 4;

        List<Microorganism> micro = new ArrayList<>();
        for (int i = 0; i < bNum; i++) {
            Microorganism m1 = new Microorganism();
            m1.setTeam(Team.GREEN);
            m1.setType(MicroOrganismType.BACTERIUM);
            micro.add(m1);

            Microorganism m2 = new Microorganism();
            m2.setTeam(Team.PURPLE);
            m2.setType(MicroOrganismType.BACTERIUM);
            micro.add(m2);
        }
        for (int i = 0; i < sNum; i++) {
            Microorganism m1 = new Microorganism();
            m1.setTeam(Team.GREEN);
            m1.setType(MicroOrganismType.SARCINE);
            micro.add(m1);

            Microorganism m2 = new Microorganism();
            m2.setTeam(Team.PURPLE);
            m2.setType(MicroOrganismType.SARCINE);
            micro.add(m2);
        }
        game.setMicroorganisms(micro);

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

        // PetriPlates
        dto.setPetriPlatesNumber(game.getPetriPlates() != null ? game.getPetriPlates().size() : 0);

        // Microorganisms
        dto.setBacteriaNumber((int) game.getMicroorganisms().stream()
                .filter(m -> m.getType() == MicroOrganismType.BACTERIUM).count() / 2); // por equipo
        dto.setSarcinesNumber((int) game.getMicroorganisms().stream()
                .filter(m -> m.getType() == MicroOrganismType.SARCINE).count() / 2); // por equipo

        // TurnManager
        dto.setTurnTeam(game.getTurn() != null ? game.getTurn().getTeam() : null);

        // Contamination
        dto.setContaminationGreen(game.getContaminationGreen() != null ? game.getContaminationGreen() : 0);
        dto.setContaminationPurple(game.getContaminationPurple() != null ? game.getContaminationPurple() : 0);

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
}