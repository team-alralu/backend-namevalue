package com.sk.namevalue.domain.name.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * title        : 이름 한줄평 엔티티
 * author       : sim
 * date         : 2023-08-29
 * description  : 이름 한줄평 엔티티
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_name_review")
public class ReviewEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_name")
    private PersonNameEntity personName;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    public ReviewEntity(PersonNameEntity personName, String content){
        this.personName = personName;
        this.content = content;
    }
}
