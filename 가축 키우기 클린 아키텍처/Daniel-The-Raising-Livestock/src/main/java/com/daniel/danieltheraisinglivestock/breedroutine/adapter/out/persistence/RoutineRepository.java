package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoutineRepository extends JpaRepository<RoutineJpaEntity, Long> {

    @Query("select r from RoutineJpaEntity r " +
            " where r.adminBreedId = :adminBreedId " +
            " and r.time >= :since")
    List<RoutineJpaEntity> findByAdminBreedIdSince(
            @Param("adminBreedId") Long adminBreedId,
            @Param("since") LocalDateTime since);

    @Query("select avg(r.health) from RoutineJpaEntity  r " +
            "where r.adminBreedId = :adminBreedId " +
            "and r.dailyBreedId = :dailyBreedId " +
            "and r.time < :until")
    Integer getAverageHealthValueUntil(
            @Param("adminBreedId") Long adminBreedId,
            @Param("dailyBreedId") Long dailyBreedId,
            @Param("until") LocalDateTime until);

}
