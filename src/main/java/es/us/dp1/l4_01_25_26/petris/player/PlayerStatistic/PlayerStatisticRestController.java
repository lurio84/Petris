package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.dto.CreateUpdatePlayerStatisticDTO;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.dto.PlayerStatisticDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/player-statistics")
@Tag(name = "PlayerStatistics", description = "PlayerStatistics management API")
@SecurityRequirement(name = "bearerAuth")
public class PlayerStatisticRestController {

    private final PlayerStatisticService playerStatisticService;

    @Autowired
    public PlayerStatisticRestController(PlayerStatisticService playerStatisticService) {
        this.playerStatisticService = playerStatisticService;
    }

    @GetMapping
    public List<PlayerStatisticDTO> getAll() {
        return playerStatisticService.getAllPlayerStatistics();
    }

    @GetMapping("/{username}")
    public PlayerStatisticDTO getByPlayerUsername(@PathVariable String username) {
        return playerStatisticService.getByPlayerUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerStatisticDTO create(@RequestBody @Valid CreateUpdatePlayerStatisticDTO dto) {
        return playerStatisticService.savePlayerStatistic(dto);
    }

    @PutMapping("/{username}")
    public PlayerStatisticDTO update(@PathVariable String username, @RequestBody @Valid CreateUpdatePlayerStatisticDTO dto) {
        return playerStatisticService.updatePlayerStatistic(username, dto);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username) {
        playerStatisticService.deletePlayerStatisticByUsername(username);
    }
}
