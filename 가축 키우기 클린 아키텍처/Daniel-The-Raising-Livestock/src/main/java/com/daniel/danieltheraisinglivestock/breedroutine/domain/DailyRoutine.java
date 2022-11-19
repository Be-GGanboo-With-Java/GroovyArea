package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

public class DailyRoutine {

    private List<Routine> routines;


    public LocalDateTime getFirstRoutineTime() {
        return routines.stream()
                .min(Comparator.comparing(Routine::getTime))
                .orElseThrow(IllegalStateException::new)
                .getTime();
    }

    public LocalDateTime getLastRoutineTime() {
        return routines.stream()
                .max(Comparator.comparing(Routine::getTime))
                .orElseThrow(IllegalStateException::new)
                .getTime();
    }

    public DailyRoutine(@NonNull List<Routine> routines) {
        this.routines = routines;
    }

    public DailyRoutine(@NonNull Routine... routines) {
        this.routines = new ArrayList<>(Arrays.asList(routines));
    }

    public List<Routine> getRoutines() {
        return Collections.unmodifiableList(this.routines);
    }

    public void addRoutine(Routine routine) {
        this.routines.add(routine);
    }
}
