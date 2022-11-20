package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import jdk.jfr.consumer.RecordedThread;
import lombok.NonNull;
import lombok.Value;

import java.math.BigInteger;

@Value
public class LiveStock {

    private static final int FULL_HEALTH = 100;
    private static final int ZERO_HEALTH = 0;

    public static LiveStock DEAD = LiveStock.of(ZERO_HEALTH);

    @NonNull
    Integer healthValue;

    public static LiveStock add(LiveStock a, LiveStock b) {
        return new LiveStock(a.healthValue + b.healthValue);
    }

    public boolean isDead() {
        return this.healthValue.compareTo(ZERO_HEALTH) == 0;
    }

    public boolean isHealthy() {
        return this.healthValue >= 80;
    }

    public boolean isWeak() {
        return this.healthValue <= 50 && this.healthValue >= 30;
    }

    public boolean isSick() {
        return this.healthValue < 30;
    }

    public LiveStock eat(LiveStock liveStock) {
        return new LiveStock(this.healthValue + liveStock.healthValue);
    }

    public LiveStock getVaccinated(LiveStock liveStock) {
        return new LiveStock(this.healthValue + liveStock.healthValue);
    }

    public LiveStock runAround(LiveStock liveStock) {
        return new LiveStock(this.healthValue + liveStock.healthValue);
    }

    public LiveStock work(LiveStock liveStock) {
        return new LiveStock(this.healthValue - liveStock.healthValue);
    }

    public static LiveStock of(int healthValue) {
        return new LiveStock(healthValue);
    }
}
