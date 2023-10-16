package com.sk.namevalue.domain.name.entity;

import com.sk.namevalue.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * title        : 네임벨류 엔티티
 * author       : sim
 * date         : 2023-10-15
 * description  : 네임벨류 엔티티
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_value")
public class ValueEntity extends BaseEntity {
    @Comment("가치 ID")
    @Column(name = "value_id")
    @Id
    private Long valueId;

    @Comment("이름")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("최솟값")
    @Column(name = "min", nullable = false)
    private int min;

    @Comment("최댓값")
    @Column(name = "max", nullable = false)
    private int max;
}
