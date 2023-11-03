package com.sk.namevalue.domain.name.entity;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import com.sk.namevalue.domain.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * title        : 이름_동물 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 이름_동물 엔티티 클래스
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_person_name_animal")
public class PersonNameAnimalEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_animal_id")
    private Long nameAnimalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_name", nullable = false)
    private PersonNameEntity personName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private AnimalEntity animal;

    public static PersonNameAnimalEntity of(PersonNameEntity personName, AnimalEntity animal){
        return new PersonNameAnimalEntity(personName, animal);
    }

    private PersonNameAnimalEntity(PersonNameEntity personName, AnimalEntity animal){
        this.personName = personName;
        this.animal = animal;
    }
}
