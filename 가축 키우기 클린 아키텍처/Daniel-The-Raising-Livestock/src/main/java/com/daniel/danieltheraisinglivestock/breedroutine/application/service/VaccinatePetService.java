package com.daniel.danieltheraisinglivestock.breedroutine.application.service;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.BreedCommand;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.VaccinatePetUseCase;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.out.RecordBreedRoutinePort;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.out.UpdateBreedDiaryPort;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@UseCase
@Transactional
@RequiredArgsConstructor
public class VaccinatePetService implements VaccinatePetUseCase {

    private final RecordBreedRoutinePort recordBreedRoutinePort;
    private final UpdateBreedDiaryPort updateBreedDiaryPort;

    @Override
    public boolean vaccinateLiveStock(BreedCommand breedCommand) {
        LocalDateTime now = LocalDateTime.now();

        Breed vaccinateBreed = recordBreedRoutinePort.recordBreedRoutine(
                breedCommand.getAdminBreedId(),
                breedCommand.getDailyBreedId(),
                now
        );

        Breed.BreedId vaccinateBreedId = vaccinateBreed.getId()
                .orElseThrow(IllegalStateException::new);

        if (!vaccinateBreed.feed(breedCommand.getLiveStock(), vaccinateBreedId)) {
            return false;
        }

        updateBreedDiaryPort.updateRoutines(vaccinateBreed);

        return true;
    }
}
