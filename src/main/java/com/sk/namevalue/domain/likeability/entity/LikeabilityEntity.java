package com.sk.namevalue.domain.likeability.entity;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * title        : 호감도 엔티티
 * author       : sim
 * date         : 2023-10-15
 * description  : 호감도 엔티티
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_likeability")

public class LikeabilityEntity extends BaseEntity {

    @Column(name = "likeability_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeabilityId;

    @ManyToOne
    @JoinColumn(name = "person_name")
    private PersonNameEntity personName;

    @Comment("호감도 (0 ~ 100)")
    @Column(name = "point", nullable = false)
    private int point;
}
