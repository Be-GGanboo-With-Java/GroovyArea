package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class Routine {

    // 루틴 일련 번호
    @Getter
    private RoutineId id;

    // 사육 총 관리자 아이디
    @Getter
    @NonNull
    private final Breed.BreedId adminBreedId;

    // 데일리 관리자 아이디
    @Getter
    @NonNull
    private final Breed.BreedId dailyBreedId;

    // 루틴 실시 시간
    @Getter
    @NonNull
    private final LocalDateTime time;

    // 사육하는 가축
    @Getter
    @NonNull
    private final LiveStock liveStock;

    public Routine(
            @NonNull Breed.BreedId adminBreedId,
            @NonNull Breed.BreedId dailyBreedId,
            @NonNull LocalDateTime time,
            @NonNull LiveStock liveStock) {
        this.id = null;
        this.adminBreedId = adminBreedId;
        this.dailyBreedId = dailyBreedId;
        this.time = time;
        this.liveStock = liveStock;
    }

    @Value
    public static class RoutineId {
        private final long id;
    }
}
