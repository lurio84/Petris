package es.us.dp1.l4_01_25_26.petris.player;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

@Service
public class PlayerService {
    PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Player getById(Integer id){
        Optional<Player> player = playerRepository.findById(id);
        return player.isPresent() ? player.get() : null;
    }

    @Transactional
    public Player savePlayer(@Valid Player player){
        playerRepository.save(player);
        return player;
    }

    @Transactional
    public Player updatePlayer(Player newPlayerInfo, Integer id){
        Player updatedPlayer = getById(id);
        BeanUtils.copyProperties(newPlayerInfo, updatedPlayer, "id");
        playerRepository.save(updatedPlayer);
        return updatedPlayer;
    }

    @Transactional
    public void deletePlayerById(Integer id){
        playerRepository.deleteById(id);
    }
}
