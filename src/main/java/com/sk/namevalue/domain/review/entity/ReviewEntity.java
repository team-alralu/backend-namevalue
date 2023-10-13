package com.sk.namevalue.domain.review.entity;

import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * title        : 이름 한줄평 엔티티
 * author       : sim
 * date         : 2023-08-29
 * description  : 이름 한줄평 엔티티
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_review")
public class ReviewEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "person_name")
    private PersonNameEntity personName;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<LikeEntity> likeList = new ArrayList<>();

    public static ReviewEntity of(PersonNameEntity personName, String content){
        return new ReviewEntity(personName,content);
    }
    private ReviewEntity(PersonNameEntity personName, String content){
        this.personName = personName;
        this.content = content;
    }
}