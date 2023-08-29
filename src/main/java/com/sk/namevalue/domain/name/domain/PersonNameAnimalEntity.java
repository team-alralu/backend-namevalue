package com.sk.namevalue.domain.name.domain;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * title        : 이름_동물 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 이름_동물 엔티티 클래스
 */

@Entity
@Getter
@Table(name = "tbl_name_animal")
public class PersonNameAnimalEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_animal_id")
    private Long nameAnimalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_name", nullable = false)
    private PersonNameEntity personName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private AnimalEntity animal;

}
