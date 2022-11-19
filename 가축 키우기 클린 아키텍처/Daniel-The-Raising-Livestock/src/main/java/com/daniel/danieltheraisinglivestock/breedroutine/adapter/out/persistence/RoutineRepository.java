package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<RoutineJpaEntity, Long> {
}
