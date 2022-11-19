package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import com.daniel.danieltheraisinglivestock.breedroutine.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<BreedJpaEntity, Long> {
}
