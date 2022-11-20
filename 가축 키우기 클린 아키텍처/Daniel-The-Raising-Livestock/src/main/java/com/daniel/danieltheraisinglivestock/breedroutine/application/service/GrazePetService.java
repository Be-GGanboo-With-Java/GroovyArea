package com.daniel.danieltheraisinglivestock.breedroutine.application.service;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.BreedCommand;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.GrazePetUseCase;
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
public class GrazePetService implements GrazePetUseCase {

    private final RecordBreedRoutinePort recordBreedRoutinePort;
    private final UpdateBreedDiaryPort updateBreedDiaryPort;

    @Override
    public boolean grazeLiveStock(BreedCommand breedCommand) {
        LocalDateTime now = LocalDateTime.now();

        Breed grazeBreed = recordBreedRoutinePort.recordBreedRoutine(
                breedCommand.getAdminBreedId(),
                breedCommand.getDailyBreedId(),
                now
        );

        Breed.BreedId grazeBreedId = grazeBreed.getId()
                .orElseThrow(IllegalStateException::new);

        if (!grazeBreed.feed(breedCommand.getLiveStock(), grazeBreedId)) {
            return false;
        }

        updateBreedDiaryPort.updateRoutines(grazeBreed);

        return true;
    }
}
