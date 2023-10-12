package com.sk.namevalue.domain.name.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * title        : 이름_성격 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 이름_성격 엔티티
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_person_name_personality")
public class PersonNamePersonalityEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_personality_id")
    private Long namePersonalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_name", nullable = false)
    private PersonNameEntity personName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personality_id", nullable = false)
    private PersonalityEntity personality;

    public static PersonNamePersonalityEntity of(PersonNameEntity personName, PersonalityEntity personality){
        return new PersonNamePersonalityEntity(personName, personality);
    }

    private PersonNamePersonalityEntity(PersonNameEntity personName, PersonalityEntity personality){
        this.personName = personName;
        this.personality = personality;
    }
}
