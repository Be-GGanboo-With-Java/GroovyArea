package com.daniel.danieltheraisinglivestock.breedroutine.adapter.in.web;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.BreedCommand;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.MakeWorkUseCase;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.VaccinatePetUseCase;
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
public class VaccinateController {

    private final VaccinatePetUseCase vaccinatePetUseCase;

    @PostMapping("/breed/vaccination/{adminBreedId}/{dailyBreedId}/{health}")
    void vaccinate(
            @PathVariable Long adminBreedId,
            @PathVariable Long dailyBreedId,
            @PathVariable int health) {

        BreedCommand vaccinateCommand = new BreedCommand(
                new Breed.BreedId(adminBreedId),
                new Breed.BreedId(dailyBreedId),
                LiveStock.of(health)
        );

        vaccinatePetUseCase.vaccinateLiveStock(vaccinateCommand);
    }
}
