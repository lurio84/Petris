package es.us.dp1.l4_01_25_26.petris.player;

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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/player")
@Tag(name ="Player", description = "Player management API endpoints")
@SecurityRequirement(name = "bearerAuth")
public class PlayerRestController {
    private final PlayerService playerService;

    @Autowired
    public PlayerRestController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return new ResponseEntity<>((List<Player>) playerService.getAllPlayers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findPlayerById(@PathVariable("id") Integer id){
        Player player = playerService.getById(id);
        if(player ==null)
            throw new ResourceNotFoundException("Player with id "+id+" not found");
        return new ResponseEntity<Player>(player,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody @Valid Player newPlayerInfo, BindingResult br){
        Player newPlayer=null;
        if(!br.hasErrors())
            newPlayer=playerService.savePlayer(newPlayerInfo);
        else
            throw new BadRequestException(br.getAllErrors());
        return new ResponseEntity<>(newPlayer,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody @Valid Player newPlayer, BindingResult br, @PathVariable("id") Integer id){
        Player playerToUpdate = this.findPlayerById(id).getBody();
        if(br.hasErrors())
            throw new BadRequestException(br.getAllErrors());
        else if (newPlayer.getId() == null || !newPlayer.getId().equals(id))
            throw new BadRequestException(("Player id or resource URL do not match!"));
        else {
            BeanUtils.copyProperties(newPlayer, playerToUpdate, "id");
            Player savedPlayer = playerService.updatePlayer(playerToUpdate, id);
            return new ResponseEntity<>(savedPlayer, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Integer id){
        findPlayerById(id);
        playerService.deletePlayerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
