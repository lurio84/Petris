package es.us.dp1.l4_01_25_26.petris.game;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
    Optional<Game> findByCode(String code);  
    void deleteByCode(String code);
}
