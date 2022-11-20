package com.daniel.danieltheraisinglivestock.breedroutine.application.port.out;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;

import java.time.LocalDateTime;

public interface RecordBreedRoutinePort {

    Breed recordBreedRoutine(Breed.BreedId adminBreedId, Breed.BreedId dailyBreedId, LocalDateTime dateTime);

}
