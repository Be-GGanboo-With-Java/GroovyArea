package com.daniel.danieltheraisinglivestock.breedroutine.application.service;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.BreedCommand;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.MakeWorkUseCase;
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
public class MakeWorkService implements MakeWorkUseCase {

    private final RecordBreedRoutinePort recordBreedRoutinePort;
    private final UpdateBreedDiaryPort updateBreedDiaryPort;

    @Override
    public boolean makeWorkLiveStock(BreedCommand breedCommand) {
        LocalDateTime now = LocalDateTime.now();

        Breed makeWorkBreed = recordBreedRoutinePort.recordBreedRoutine(
                breedCommand.getAdminBreedId(),
                breedCommand.getDailyBreedId(),
                now
        );

        Breed.BreedId makeWorkBreedId = makeWorkBreed.getId()
                .orElseThrow(IllegalStateException::new);

        if (!makeWorkBreed.feed(breedCommand.getLiveStock(), makeWorkBreedId)) {
            return false;
        }

        updateBreedDiaryPort.updateRoutines(makeWorkBreed);

        return true;
    }
}
