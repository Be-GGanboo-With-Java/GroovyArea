package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "breed")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
class BreedJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
