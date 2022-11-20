package com.daniel.danieltheraisinglivestock.breedroutine.application.port.in;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed.BreedId;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.LiveStock;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class BreedCommand {

    @NotNull
    BreedId adminBreedId;

    @NotNull
    BreedId dailyBreedId;

    @NotNull
    LiveStock liveStock;

    public BreedCommand(
            BreedId adminBreedId,
            BreedId dailyBreedId,
            LiveStock liveStock) {
        this.adminBreedId = adminBreedId;
        this.dailyBreedId = dailyBreedId;
        this.liveStock = liveStock;
    }
}
