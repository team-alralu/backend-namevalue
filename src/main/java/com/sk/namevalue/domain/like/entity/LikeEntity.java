package com.sk.namevalue.domain.like.entity;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * title        : 좋아요 엔티티
 * author       : sim
 * date         : 2023-10-13
 * description  : 좋아요 엔티티
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_like")
public class LikeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    private LikeEntity(UserEntity userEntity, ReviewEntity reviewEntity){
        this.user = userEntity;
        this.review = reviewEntity;
    }

    public static LikeEntity of(UserEntity userEntity, ReviewEntity reviewEntity){
        return new LikeEntity(userEntity, reviewEntity);
    }
}
