package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetriPlateRepository extends CrudRepository<PetriPlate, Integer> {
    @Query("SELECT pt FROM PetriPlate pt WHERE pt.position = :position")
    Optional<PetriPlate> findByPosition(Integer position);
}
