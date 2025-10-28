package es.us.dp1.l4_01_25_26.petris.statistic;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.us.dp1.l4_01_25_26.petris.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerStadisticService {
    PlayerStadisticRepository playerStadisticRepository;

    @Autowired
    public PlayerStadisticService(PlayerStadisticRepository playerStadisticRepository){
        this.playerStadisticRepository=playerStadisticRepository;
    }

    @Transactional
    public PlayerStadistic savePlayerStadistic(PlayerStadistic ps){
        playerStadisticRepository.save(ps);
        return ps;
    }

    @Transactional(readOnly = true)
    public PlayerStadistic findPlayerStadistic(Integer id) {
        return playerStadisticRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PlayerStadistic", "id", id));
    }

    @Transactional(readOnly = true)
    public Iterable<PlayerStadistic> findAll() {
        return playerStadisticRepository.findAll(); 
    }

    @Transactional
    public PlayerStadistic updatePlayerStadistic(PlayerStadistic new_ps, Integer id) {
        PlayerStadistic updated_ps = findPlayerStadistic(id);
        BeanUtils.copyProperties(new_ps, updated_ps, "id");
        playerStadisticRepository.save(updated_ps);
        return updated_ps;
    }


}