package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PetriPlateService {

    private final PetriPlateRepository repository;

    public PetriPlateService(PetriPlateRepository repository) {
        this.repository = repository;
    }

    public static final Map<Integer, List<Integer>> adjacentMap = Map.of(
            0, List.of(1, 5, 6),
            1, List.of(0, 2, 6),
            2, List.of(1, 3, 6),
            3, List.of(2, 4, 6),
            4, List.of(3, 5, 6),
            5, List.of(4, 0, 6),
            6, List.of(0, 1, 2, 3, 4, 5));

    // CRUD

    @Transactional
    public List<PetriPlate> findAll() {
        List<PetriPlate> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    public Optional<PetriPlate> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<PetriPlate> findByPosition(Integer position) {
        return repository.findByPosition(position);
    }

    @Transactional
    public PetriPlate create(PetriPlate data) {
        return repository.save(data);
    }

    @Transactional
    public Optional<PetriPlate> update(Integer id, PetriPlate data) {
        return repository.findById(id).map(existing -> {

            existing.setPosition(data.getPosition());
            existing.setGreenBacteria(data.getGreenBacteria());
            existing.setGreenSarcines(data.getGreenSarcines());
            existing.setPurpleBacteria(data.getPurpleBacteria());
            existing.setPurpleSarcines(data.getPurpleSarcines());

            return repository.save(existing);
        });
    }

    @Transactional
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // =============== INICIALIZACIÓN DE TABLERO ===============

    /**
     * Crea las placas del tablero con posiciones 0..platesNumber-1.
     * Normalmente platesNumber = 7.
     */
    @Transactional
    public List<PetriPlate> initializeBoard(int platesNumber) {
        List<PetriPlate> plates = new ArrayList<>();

        for (int pos = 0; pos < platesNumber; pos++) {
            PetriPlate plate = new PetriPlate();
            plate.setPosition(pos);
            plates.add(repository.save(plate));
        }

        return plates;
    }

    // =============== ADYACENCIAS ===============

    /** Devuelve true si dos posiciones del tablero son adyacentes. */
    public boolean checkAdjacent(int fromPosition, int toPosition) {
        return adjacentMap
                .getOrDefault(fromPosition, List.of())
                .contains(toPosition);
    }

    /** Versión que recibe placas directamente. */
    public boolean checkAdjacent(PetriPlate from, PetriPlate to) {
        return checkAdjacent(from.getPosition(), to.getPosition());
    }

    /** Devuelve las placas adyacentes a la posición indicada. */
    public List<PetriPlate> getAdjacentPlates(int originPosition) {
        List<Integer> neighbours = adjacentMap.getOrDefault(originPosition, List.of());
        List<PetriPlate> result = new ArrayList<>();

        for (Integer pos : neighbours) {
            repository.findByPosition(pos).ifPresent(result::add);
        }

        return result;
    }

    // =============== LÓGICA DE CANTIDADES (Bacterias/Sarcinas) ===============

    /**
     * Añade una bacteria verde en la placa y comprueba posible conversión a
     * sarcina.
     */
    @Transactional
    public void addGreenBacterium(int position) {
        PetriPlate plate = repository.findByPosition(position)
                .orElseThrow(() -> new IllegalArgumentException("No existe placa en posición " + position));

        plate.setGreenBacteria(plate.getGreenBacteria() + 1);
        applySarcineConversion(plate);
        repository.save(plate);
    }

    /**
     * Añade una bacteria morada en la placa y comprueba posible conversión a
     * sarcina.
     */
    @Transactional
    public void addPurpleBacterium(int position) {
        PetriPlate plate = repository.findByPosition(position)
                .orElseThrow(() -> new IllegalArgumentException("No existe placa en posición " + position));

        plate.setPurpleBacteria(plate.getPurpleBacteria() + 1);
        applySarcineConversion(plate);
        repository.save(plate);
    }

    /**
     * Lógica equivalente a la antigua sarcineConversion:
     * - si hay 5 bacterias verdes → se convierten en 1 sarcina verde.
     * - si hay 5 bacterias moradas → se convierten en 1 sarcina morada.
     * Se aplica solo una conversión por llamada, como en vuestro código original.
     *
     * @return true si ha habido alguna conversión.
     */
    @Transactional
    public boolean applySarcineConversion(PetriPlate plate) {
        boolean greenConversion = plate.getGreenBacteria() == 5;
        boolean purpleConversion = plate.getPurpleBacteria() == 5;

        boolean changed = false;

        if (greenConversion) {
            plate.setGreenBacteria(0);
            plate.setGreenSarcines(plate.getGreenSarcines() + 1);
            changed = true;
        } else if (purpleConversion) {
            plate.setPurpleBacteria(0);
            plate.setPurpleSarcines(plate.getPurpleSarcines() + 1);
            changed = true;
        }

        return changed;
    }

    /**
     * Versión pública que solo recibe la posición.
     */
    @Transactional
    public boolean checkSarcineConversion(int position) {
        PetriPlate plate = repository.findByPosition(position)
                .orElseThrow(() -> new IllegalArgumentException("No existe placa en posición " + position));
        boolean changed = applySarcineConversion(plate);
        if (changed) {
            repository.save(plate);
        }
        return changed;
    }
}
