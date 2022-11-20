package com.daniel.danieltheraisinglivestock.breedroutine.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "routine")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class RoutineJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime time;

    @Column
    private Long adminBreedId;

    @Column
    private Long dailyBreedId;

    @Column
    private int health;

}
