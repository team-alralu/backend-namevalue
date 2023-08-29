package com.sk.namevalue.domain.animal.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.name.domain.PersonNameAnimalEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

/**
 * title        : 동물 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 동물 엔티티 클래스
 */

@Entity
@Getter
@Table(name = "tbl_animal")
public class AnimalEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "emoji", nullable = false)
    private String emoji;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonNameAnimalEntity> animalEntityList;
}
