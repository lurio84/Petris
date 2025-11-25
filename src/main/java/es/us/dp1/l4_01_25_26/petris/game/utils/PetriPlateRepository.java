package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PetriPlateRepository extends CrudRepository<PetriPlate, Integer> {
    List<PetriPlate> findAll();
    Optional<PetriPlate> findById(Integer id);

    @Query("SELECT pt FROM PetriPlate pt WHERE pt.position = :position")
    List<PetriPlate> findByPosition(Integer position);

    //HAbría que añadir un Delet by Game id si tuvieramos persistencia en BBDD
}
