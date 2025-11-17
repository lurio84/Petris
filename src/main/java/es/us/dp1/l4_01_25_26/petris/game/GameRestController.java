package es.us.dp1.l4_01_25_26.petris.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.us.dp1.l4_01_25_26.petris.game.dto.GameDTO;
import es.us.dp1.l4_01_25_26.petris.game.dto.StartGameDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
@Tag(name = "Games", description = "The Games management API")
@SecurityRequirement(name = "bearerAuth")
public class GameRestController {

    private final GameService gameService;

    @Autowired
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{code}")
    public GameDTO getGameByCode(@PathVariable String code) {
        return gameService.getByCode(code); 
    }

    @PostMapping
    public Game postGame(@Valid @RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PutMapping
    public Game putGame(@Valid @RequestBody Game game) {
        return gameService.updateGame(game);
    }

    @DeleteMapping("/{code}")
    public void deleteGameByCode(@PathVariable String code) {
        gameService.deleteByCode(code);
    }

    @PostMapping("/start")
    public GameDTO startGame(@Valid @RequestBody StartGameDTO gameDTO) {
        return gameService.startGame(gameDTO);
    }

}
