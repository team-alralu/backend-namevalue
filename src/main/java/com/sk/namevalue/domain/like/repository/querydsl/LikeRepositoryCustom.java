package com.sk.namevalue.domain.like.repository.querydsl;

import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.user.domain.UserEntity;

/**
 * title        : LikeRepositoryCustom
 * author       : sim
 * date         : 2023-10-13
 * description  : LikeRepositoryCustom
 */
public interface LikeRepositoryCustom {

    LikeEntity findByUserAndReview(UserEntity user, ReviewEntity reviewEntity);
}
