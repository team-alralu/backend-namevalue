package com.sk.namevalue.domain.name.domain;

import jakarta.persistence.*;
import lombok.Getter;

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
public class NameEntity {

    @Id
    @Column(name = "person_name", nullable = false)
    private String personName;

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<NamePersonalityEntity> personalityList;

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<NameAnimalEntity> animalList;
}
