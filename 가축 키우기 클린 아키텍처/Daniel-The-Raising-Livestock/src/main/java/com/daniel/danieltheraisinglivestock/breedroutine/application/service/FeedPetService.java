package com.daniel.danieltheraisinglivestock.breedroutine.application.service;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.BreedCommand;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.in.FeedPetUseCase;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.out.RecordBreedRoutinePort;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.out.UpdateBreedDiaryPort;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed.BreedId;
import com.daniel.danieltheraisinglivestock.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FeedPetService implements FeedPetUseCase {

    private final RecordBreedRoutinePort recordBreedRoutinePort;
    private final UpdateBreedDiaryPort updateBreedDiaryPort;

    @Override
    public boolean feedLiveStock(BreedCommand breedCommand) {
        LocalDateTime now = LocalDateTime.now();

        Breed feedBreed = recordBreedRoutinePort.recordBreedRoutine(
                breedCommand.getAdminBreedId(),
                breedCommand.getDailyBreedId(),
                now
        );

        BreedId feedBreedId = feedBreed.getId()
                .orElseThrow(IllegalStateException::new);

        if (!feedBreed.feed(breedCommand.getLiveStock(), feedBreedId)) {
            return false;
        }

        updateBreedDiaryPort.updateRoutines(feedBreed);

        return true;
    }
}
