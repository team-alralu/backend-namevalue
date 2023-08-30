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
@Table(name = "tbl_name_personality")
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

    /**
     * PersonNamePersonalityEntity 생성자
     * @param personName - 사람 이름 엔티티
     * @param personality - 성격 엔티티
     * 같은 패키지에 위치한 엔티티 클래스에서만 사용할 수 있도록 접근 제어자를 protected 로 선언함.
     */
    protected PersonNamePersonalityEntity(PersonNameEntity personName, PersonalityEntity personality){
        this.personName = personName;
        this.personality = personality;
    }
}
