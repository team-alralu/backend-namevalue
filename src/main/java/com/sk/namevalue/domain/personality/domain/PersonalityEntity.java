package com.sk.namevalue.domain.personality.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personality_id")
    private Long personalityId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "emoji", nullable = false)
    private String emoji;

    @Column(name = "point", nullable = false)
    private int point;

    public static PersonalityEntity createPersonalityEntity(String name, String emoji, int point){
        return new PersonalityEntity(name, emoji, point);
    }
    private PersonalityEntity(String name, String emoji, int point){
        this.name = name;
        this.emoji = emoji;
        this.point = point;
    }
}
