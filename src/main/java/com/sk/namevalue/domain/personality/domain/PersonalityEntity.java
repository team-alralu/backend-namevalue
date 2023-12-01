package com.sk.namevalue.domain.personality.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.name.entity.PersonNamePersonalityEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * title        : 성격 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 성격 엔티티 클래스
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_personality")
public class PersonalityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id")
    private Long personalityId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @OneToMany(mappedBy = "personality", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonNamePersonalityEntity> personalityEntityList = new ArrayList<>();

    private PersonalityEntity(Long personalityId, String name, String path){
        this.personalityId = personalityId;
        this.name = name;
        this.path = path;
    }
    public static PersonalityEntity of(Long personalityId, String name, String path){
        return new PersonalityEntity(personalityId, name, path);
    }
}
