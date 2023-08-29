package com.sk.namevalue.domain.name.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * title        : 이름 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 사람 이름 엔티티
 */

@Entity
@Getter
@Table(name = "tbl_name")
public class PersonNameEntity {

    @Id
    @Column(name = "person_name", nullable = false)
    private String personName;

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<PersonNamePersonalityEntity> personalityList = new ArrayList<>();

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<PersonNameAnimalEntity> animalList = new ArrayList<>();

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList = new ArrayList<>();
}
