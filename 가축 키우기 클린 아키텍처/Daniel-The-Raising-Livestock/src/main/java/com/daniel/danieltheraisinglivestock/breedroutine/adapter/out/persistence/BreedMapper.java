package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.DailyRoutine;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.LiveStock;
import com.daniel.danieltheraisinglivestock.breedroutine.domain.Routine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class BreedMapper {

    Breed toDomainEntity(
            BreedJpaEntity breedJpaEntity,
            List<RoutineJpaEntity> routineJpaEntities,
            Integer healthValue) {

        LiveStock liveStock = LiveStock.of(healthValue);

        return Breed.withId(
                new Breed.BreedRoutineId(breedJpaEntity.getId()),
                liveStock,
                toDailyRoutine(routineJpaEntities));
    }

    DailyRoutine toDailyRoutine(List<RoutineJpaEntity> routineJpaEntities) {
        List<Routine> routines = new ArrayList<>();

        for (RoutineJpaEntity routine : routineJpaEntities) {
            routines.add(new Routine(
                    new Routine.RoutineId(routine.getId()),
                    new Breed.BreedRoutineId(routine.getAdminBreedId()),
                    new Breed.BreedRoutineId(routine.getDailyBreedId()),
                    routine.getTime(),
                    LiveStock.of(routine.getHealth())

            ));
        }
        return new DailyRoutine(routines);
    }

    RoutineJpaEntity toJpaEntity(Routine routine) {
        return new RoutineJpaEntity(
                routine.getId() == null ? null : routine.getId().getId(),
                routine.getTime(),
                routine.getAdminBreedId().getId(),
                routine.getDailyBreedId().getId(),
                routine.getLiveStock().getHealthValue());
    }
}
