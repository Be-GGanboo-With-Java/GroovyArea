package com.daniel.danieltheraisinglivestock.breedroutine.domain;

import java.util.List;

public class DailyRoutine {

    private List<Routine> routines;

    public void addRoutine(Routine routine) {
        this.routines.add(routine);
    }
}
