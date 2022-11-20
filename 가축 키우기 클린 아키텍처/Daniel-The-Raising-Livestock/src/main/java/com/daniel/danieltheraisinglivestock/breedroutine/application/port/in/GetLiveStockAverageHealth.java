package com.daniel.danieltheraisinglivestock.breedroutine.application.port.in;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.LiveStock;

public interface GetLiveStockAverageHealth {

    LiveStock getAverageHealth(Breed.BreedId breedId);

}
