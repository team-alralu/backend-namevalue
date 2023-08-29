package com.sk.namevalue.domain.name.domain;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * title        : 이름_성격 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 이름_성격 엔티티
 */

@Entity
@Getter
@Table(name = "tbl_name_personality")
public class NamePersonalityEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_personality_id")
    private Long namePersonalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_name", nullable = false)
    private NameEntity personName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personality_id", nullable = false)
    private PersonalityEntity personality;
}
