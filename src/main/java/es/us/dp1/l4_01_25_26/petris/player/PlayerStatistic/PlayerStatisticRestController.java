package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.dp1.l4_01_25_26.petris.exceptions.BadRequestException;
import es.us.dp1.l4_01_25_26.petris.exceptions.ResourceNotFoundException;
import es.us.dp1.l4_01_25_26.petris.statistic.Achievement;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/playerstatistics")
@Tag(name = "PlayerStatistics", description = "The PlayerStatistics management API")
@SecurityRequirement(name = "bearerAuth")
public class PlayerStatisticRestController {

    private final PlayerStatisticService playerStatisticService;

    @Autowired
    public PlayerStatisticRestController(PlayerStatisticService playerStatisticService) {
        this.playerStatisticService = playerStatisticService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerStatistic>> findAll() {
        return new ResponseEntity<>((List<PlayerStatistic>) playerStatisticService.getAllPlayerStatistics(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerStatistic> findPlayerStatistic(@PathVariable("id") Integer id){
        PlayerStatistic playerStatisticToGet=playerStatisticService.getById(id);
        if (playerStatisticToGet==null)
            throw new ResourceNotFoundException("PlayerStatistic with id "+id+" not found!");
        return new ResponseEntity<PlayerStatistic>(playerStatisticToGet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayerStatistic> createPlayerStatistic(@RequestBody @Valid PlayerStatistic newps, BindingResult br){
        PlayerStatistic result=null;
        if(!br.hasErrors())
            result=playerStatisticService.savePlayerStatistic(newps);
        else
            throw new BadRequestException(br.getAllErrors());
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PlayerStatistic> updatePlayerStatistic(@RequestBody @Valid PlayerStatistic new_ps, BindingResult br, @PathVariable("id") Integer id){
        PlayerStatistic playerStatisticToUpdate = this.findPlayerStatistic(id).getBody();
        if (br.hasErrors())
            throw new BadRequestException(br.getAllErrors());
        else if (new_ps.getId() == null || !new_ps.getId().equals(id))
            throw new BadRequestException("PlayerStatistic id and resource URL id do not match!");
        else {
            BeanUtils.copyProperties(new_ps, playerStatisticToUpdate, "id");
            PlayerStatistic saved = playerStatisticService.updatePlayerStatistic(playerStatisticToUpdate, id);
            return new ResponseEntity<>(saved, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerStatistic(@PathVariable("id") Integer id){
        findPlayerStatistic(id);
        playerStatisticService.deletePlayerStatisticById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}