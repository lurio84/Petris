package es.us.dp1.l4_01_25_26.petris.game.utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import es.us.dp1.l4_01_25_26.petris.game.Game;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PetriPlateServiceTests {

    @Autowired
    private PetriPlateService petriPlateService;

    @Autowired
    private PetriPlateRepository petriPlateRepository;

    @Test
    void initializeBoard_createsSevenPlatesWithCorrectPositions() {
        // Arrange & Act
        List<PetriPlate> plates = petriPlateService.initializeBoard(new Game(), 7);

        // Assert
        assertThat(plates).hasSize(7);
        assertThat(plates)
                .extracting(PetriPlate::getPosition)
                .containsExactlyInAnyOrder(0, 1, 2, 3, 4, 5, 6);
    }

    @Test
    void checkAdjacent_returnsTrueForNeighboursAndFalseOtherwise() {
        petriPlateService.initializeBoard(new Game(), 7);

        // 0 es adyacente a 1,5,6
        assertThat(petriPlateService.checkAdjacent(0, 1)).isTrue();
        assertThat(petriPlateService.checkAdjacent(0, 5)).isTrue();
        assertThat(petriPlateService.checkAdjacent(0, 6)).isTrue();

        // 0 NO es adyacente a 2
        assertThat(petriPlateService.checkAdjacent(0, 2)).isFalse();
    }

    @Test
    void addGreenBacterium_triggersSarcineConversionOnlyOnFission() {
        petriPlateService.initializeBoard(new Game(), 7);

        // Añadimos 5 bacterias verdes en la posición 0 simulando 5 fisiones
        for (int i = 0; i < 5; i++) {
            petriPlateService.addGreenBacterium(0); // esta SÍ llama a applySarcineConversion
        }

        PetriPlate plate0 = petriPlateRepository.findByPosition(0).orElseThrow();

        // Después de la conversión:
        assertThat(plate0.getGreenBacteria()).isZero();
        assertThat(plate0.getGreenSarcines()).isEqualTo(1);
    }

}
