package es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.l4_01_25_26.petris.player.Player;
import es.us.dp1.l4_01_25_26.petris.player.PlayerRepository;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.dto.CreateUpdatePlayerStatisticDTO;
import es.us.dp1.l4_01_25_26.petris.player.PlayerStatistic.dto.PlayerStatisticDTO;
import es.us.dp1.l4_01_25_26.petris.exceptions.ResourceNotFoundException;

@Service
public class PlayerStatisticService {

    private final PlayerStatisticRepository playerStatisticRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerStatisticService(PlayerStatisticRepository playerStatisticRepository,
            PlayerRepository playerRepository) {
        this.playerStatisticRepository = playerStatisticRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public List<PlayerStatisticDTO> getAllPlayerStatistics() {
        return playerStatisticRepository.findAll()
                .stream()
                .map(PlayerStatisticDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlayerStatisticDTO getByPlayerUsername(String username) {
        PlayerStatistic ps = playerStatisticRepository.findByPlayerUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("PlayerStatistic of username " + username + " not found."));
        return new PlayerStatisticDTO(ps);
    }

    @Transactional
    public PlayerStatisticDTO savePlayerStatistic(CreateUpdatePlayerStatisticDTO  dto) {
        Player player = playerRepository.findByUserUsername(dto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Player " + dto.getUsername() + " not found"));

        PlayerStatistic ps = new PlayerStatistic();
        ps.setPlayer(player);
        ps.setFriends(dto.getFriends());
        ps.setFirstConnection(dto.getFirstConnection());
        ps.setLastConnection(dto.getLastConnection());
        ps.setGamesPlayed(dto.getGamesPlayed());
        ps.setGamesWon(dto.getGamesWon());
        ps.setGamesAsGreen(dto.getGamesAsGreen());
        ps.setGamesAsPurple(dto.getGamesAsPurple());
        ps.setFavouriteTeam(dto.getFavouriteTeam());
        ps.setVictoriesAsGreen(dto.getVictoriesAsGreen());
        ps.setVictoriesAsPurple(dto.getVictoriesAsPurple());
        ps.setMaxBacteryPlayedAsGreen(dto.getMaxBacteryPlayedAsGreen());
        ps.setMaxBacteryPlayedAsPurple(dto.getMaxBacteryPlayedAsPurple());
        ps.setMaxSarcinePlayedAsGreen(dto.getMaxSarcinePlayedAsGreen());
        ps.setMaxSarcinePlayedAsPurple(dto.getMaxSarcinePlayedAsPurple());
        ps.setMaxTurnsPlayedAsGreen(dto.getMaxTurnsPlayedAsGreen());
        ps.setMaxTurnsPlayedAsPurple(dto.getMaxTurnsPlayedAsPurple());

        PlayerStatistic saved = playerStatisticRepository.save(ps);
        return new PlayerStatisticDTO(saved);
    }

    @Transactional
    public PlayerStatisticDTO updatePlayerStatistic(String username, CreateUpdatePlayerStatisticDTO  dto) {
        PlayerStatistic ps = playerStatisticRepository.findByPlayerUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("PlayerStatistic of username " + username + " not found."));

        ps.setFriends(dto.getFriends());
        ps.setFirstConnection(dto.getFirstConnection());
        ps.setLastConnection(dto.getLastConnection());
        ps.setGamesPlayed(dto.getGamesPlayed());
        ps.setGamesWon(dto.getGamesWon());
        ps.setGamesAsGreen(dto.getGamesAsGreen());
        ps.setGamesAsPurple(dto.getGamesAsPurple());
        ps.setFavouriteTeam(dto.getFavouriteTeam());
        ps.setVictoriesAsGreen(dto.getVictoriesAsGreen());
        ps.setVictoriesAsPurple(dto.getVictoriesAsPurple());
        ps.setMaxBacteryPlayedAsGreen(dto.getMaxBacteryPlayedAsGreen());
        ps.setMaxBacteryPlayedAsPurple(dto.getMaxBacteryPlayedAsPurple());
        ps.setMaxSarcinePlayedAsGreen(dto.getMaxSarcinePlayedAsGreen());
        ps.setMaxSarcinePlayedAsPurple(dto.getMaxSarcinePlayedAsPurple());
        ps.setMaxTurnsPlayedAsGreen(dto.getMaxTurnsPlayedAsGreen());
        ps.setMaxTurnsPlayedAsPurple(dto.getMaxTurnsPlayedAsPurple());

        PlayerStatistic updated = playerStatisticRepository.save(ps);
        return new PlayerStatisticDTO(updated);
    }

    @Transactional
    public void deletePlayerStatisticByUsername(String username) {
        if (!playerStatisticRepository.existsByPlayerUserUsername(username)) {
            throw new ResourceNotFoundException("PlayerStatistic of username " + username + " not found.");
        }
        playerStatisticRepository.deleteByPlayerUserUsername(username);
    }
}
