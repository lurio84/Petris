package es.us.dp1.l4_01_25_26.petris.player;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    Optional<Player> findByUserUsername(String username);

}
