package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import com.daniel.danieltheraisinglivestock.breedroutine.application.port.out.RecordBreedRoutinePort;
import com.daniel.danieltheraisinglivestock.breedroutine.application.port.out.UpdateBreedDiaryPort;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.DailyRoutine;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Routine;
import com.daniel.danieltheraisinglivestock.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class BreedPersistenceAdapter implements UpdateBreedDiaryPort, RecordBreedRoutinePort {

    private final BreedRepository breedRepository;
    private final RoutineRepository routineRepository;
    private final BreedMapper breedMapper;

    @Override
    public Breed recordBreedRoutine(
            Breed.BreedId adminBreedId,
            Breed.BreedId dailyBreedId,
            LocalDateTime dateTime) {

        BreedJpaEntity breed = breedRepository.findById(adminBreedId.getId())
                .orElseThrow(EntityNotFoundException::new);

        List<RoutineJpaEntity> routines = routineRepository.findByAdminBreedIdSince(
                adminBreedId.getId(),
                dateTime);

        Integer avgHealthValue = orZero(routineRepository.getAverageHealthValueUntil(
                adminBreedId.getId(),
                dailyBreedId.getId(),
                dateTime
        ));

        return breedMapper.toDomainEntity(
                breed,
                routines,
                avgHealthValue);
    }

    private Integer orZero(Integer value) {
        return value == null ? 0 : value;
    }

    @Override
    public void updateRoutines(Breed breed) {
        for (Routine routine : breed.getDailyRoutine().getRoutines()) {
            if (routine.getId() == null) {
                routineRepository.save(breedMapper.toJpaEntity(routine));
            }
        }
    }
}
