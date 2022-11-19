package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 가축 사육 루틴 도메인 엔티티
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BreedRoutine {

    private final BreedRoutineId id;

    @Getter
    private final LiveStock liveStock;

    @Getter
    private final DailyRoutine dailyRoutine;

    public static BreedRoutine withoutId(
            LiveStock liveStock,
            DailyRoutine dailyActivity) {
        return new BreedRoutine(null, liveStock, dailyActivity);
    }

    public static BreedRoutine withId(
            BreedRoutineId breedRoutineId,
            LiveStock liveStock,
            DailyRoutine dailyActivity) {
        return new BreedRoutine(breedRoutineId, liveStock, dailyActivity);
    }

    public Optional<BreedRoutineId> getId() {
        return Optional.ofNullable(this.id);
    }

    // 먹이 주는 행위
    public boolean feed(LiveStock liveStock, BreedRoutineId dailyBreedId) {
        Routine feeding = new Routine(
                this.id,
                dailyBreedId,
                LocalDateTime.now(),
                liveStock
        );
        this.dailyRoutine.addRoutine(feeding);
        return true;
    }

    // 백신 맞히는 행위
    public boolean vaccinate(LiveStock liveStock, BreedRoutineId dailyBreedId) {
        Routine vaccinating = new Routine(
                this.id,
                dailyBreedId,
                LocalDateTime.now(),
                liveStock
        );
        this.dailyRoutine.addRoutine(vaccinating);
        return true;
    }

    // 방목시키는 행위
    public boolean graze(LiveStock liveStock, BreedRoutineId dailyRoutineId) {
        Routine grazing = new Routine(
                this.id,
                dailyRoutineId,
                LocalDateTime.now(),
                liveStock
        );
        this.dailyRoutine.addRoutine(grazing);
        return true;
    }

    public boolean makeWork(LiveStock liveStock, BreedRoutineId dailyRoutineId) {
        Routine makeWorking = new Routine(
                this.id,
                dailyRoutineId,
                LocalDateTime.now(),
                liveStock
        );
        this.dailyRoutine.addRoutine(makeWorking);
        return true;
    }

    @Value
    public static class BreedRoutineId {
        private long id;
    }
}
