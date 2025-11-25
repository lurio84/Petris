package es.us.dp1.l4_01_25_26.petris.game.utils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/petri-plates")
public class PetriPlateRestController {

    private final PetriPlateService petriPlateService;

    public PetriPlateRestController(PetriPlateService petriPlateService) {
        this.petriPlateService = petriPlateService;
    }

}
