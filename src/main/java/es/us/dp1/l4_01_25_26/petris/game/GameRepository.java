package es.us.dp1.l4_01_25_26.petris.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findByCode(String code);  
    void deleteByCode(String code);
}
