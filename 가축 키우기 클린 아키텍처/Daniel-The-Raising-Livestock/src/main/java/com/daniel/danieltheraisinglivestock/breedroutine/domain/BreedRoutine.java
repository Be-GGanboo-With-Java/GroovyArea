package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import com.daniel.danieltheraisinglivestock.livestock.domain.LiveStock;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 가축 도메인 엔티티
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
        liveStock.eat(liveStock);

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
    public boolean vaccinate() {
        return
    }

    // 방목시키는 행위
    public boolean graze() {
        return
    }

    @Value
    public static class BreedRoutineId {
        private long id;
    }
}
