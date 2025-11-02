package es.us.dp1.l4_01_25_26.petris.game;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Game getByCode(String code) {
        return gameRepository.findByCode(code);
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
}
