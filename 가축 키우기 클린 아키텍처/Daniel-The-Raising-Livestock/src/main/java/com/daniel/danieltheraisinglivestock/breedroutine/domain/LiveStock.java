package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import lombok.NonNull;
import lombok.Value;

import java.math.BigInteger;

@Value
public class LiveStock {

    private static final int FULL_HEALTH = 100;

    // 죽은 가축
    public static final LiveStock DEAD = LiveStock.of(0);

    @NonNull
    private final Integer healthValue;

    public static LiveStock add(LiveStock a, LiveStock b) {
        return new LiveStock(a.healthValue + b.healthValue);
    }

    public LiveStock eat(LiveStock liveStock) {
        return new LiveStock(this.healthValue + liveStock.healthValue);
    }

    public static LiveStock of(int healthValue) {
        return new LiveStock(healthValue);
    }
}
