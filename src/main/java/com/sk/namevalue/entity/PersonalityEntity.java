package com.sk.namevalue.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * title        : 성격 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 성격 엔티티 클래스
 */

@Entity
@Getter
@Table(name = "tbl_personality")
public class PersonalityEntity {

    @Id @GeneratedValue
    @Column(name = "personality_id")
    private Long personalityId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "emoji", nullable = false)
    private String emoji;

    @Column(name = "point", nullable = false)
    private int point;
}
