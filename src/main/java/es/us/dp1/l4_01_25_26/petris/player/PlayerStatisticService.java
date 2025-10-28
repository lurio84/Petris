package es.us.dp1.l4_01_25_26.petris.player;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.us.dp1.l4_01_25_26.petris.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerStatisticService {
    PlayerStatisticRepository playerStatisticRepository;

    @Autowired
    public PlayerStatisticService(PlayerStatisticRepository playerStadisticRepository){
        this.playerStatisticRepository=playerStadisticRepository;
    }



    @Transactional(readOnly = true)
    public List<PlayerStatistic> getPlayerStatistics() {
        return playerStatisticRepository.findAll(); 
    }

    

    @Transactional(readOnly = true)
    public PlayerStatistic getById(Integer id) {
        return playerStatisticRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PlayerStatistic", "id", id));
    }

    @Transactional
    public PlayerStatistic savePlayerStatistic(@Valid PlayerStatistic ps){
        playerStatisticRepository.save(ps);
        return ps;
    }

    @Transactional
    public PlayerStatistic updatePlayerStatistic(PlayerStatistic new_ps, Integer id) {
        PlayerStatistic updated_ps = getById(id);
        BeanUtils.copyProperties(new_ps, updated_ps, "id");
        playerStatisticRepository.save(updated_ps);
        return updated_ps;
    }

    @Transactional
    public void deletePlayerStatisticById(Integer id){
        playerStatisticRepository.deleteById(id);
    }

}