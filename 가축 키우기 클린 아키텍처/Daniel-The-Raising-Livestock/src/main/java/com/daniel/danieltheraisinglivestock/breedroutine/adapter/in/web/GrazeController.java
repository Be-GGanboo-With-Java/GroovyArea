package com.daniel.danieltheraisinglivestock.breedroutine.adapter.in.web;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.BreedCommand;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.FeedPetUseCase;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.GrazePetUseCase;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.LiveStock;
import com.daniel.danieltheraisinglivestock.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GrazeController {

    private final GrazePetUseCase grazePetUseCase;

    @PostMapping("/breed/graze/{adminBreedId}/{dailyBreedId}/{health}")
    void graze(
            @PathVariable Long adminBreedId,
            @PathVariable Long dailyBreedId,
            @PathVariable int health) {

        BreedCommand grazeCommand = new BreedCommand(
                new Breed.BreedId(adminBreedId),
                new Breed.BreedId(dailyBreedId),
                LiveStock.of(health)
        );

        grazePetUseCase.grazeLiveStock(grazeCommand);
    }
}
