package es.us.dp1.l4_01_25_26.petris.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.us.dp1.l4_01_25_26.petris.player.dto.CreateEditPlayerDTO;
import es.us.dp1.l4_01_25_26.petris.player.dto.PlayerDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/players")
@Tag(name = "Player", description = "Player management API endpoints")
@SecurityRequirement(name = "bearerAuth")
public class PlayerRestController {

    private final PlayerService playerService;

    @Autowired
    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Integer id) {
        return playerService.getById(id);
    }

    @PostMapping
    public PlayerDTO createPlayer(@RequestBody CreateEditPlayerDTO player) {
        return playerService.postPlayer(player);
    }

    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@PathVariable Integer id,@RequestBody CreateEditPlayerDTO dto) {
        return playerService.updatePlayer(id,dto);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayerById(id);
    }
}
