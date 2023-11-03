package com.sk.namevalue.domain.animal.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.name.entity.PersonNameAnimalEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimalEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long animalId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonNameAnimalEntity> animalEntityList = new ArrayList<>();

    public AnimalEntity(String name){
        this.name = name;
    }
}
