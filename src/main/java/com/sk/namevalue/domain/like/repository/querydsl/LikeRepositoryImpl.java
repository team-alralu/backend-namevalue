package com.sk.namevalue.domain.like.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;

import static com.sk.namevalue.domain.like.entity.QLikeEntity.likeEntity;


/**
 * title        : LikeRepositoryImpl
 * author       : sim
 * date         : 2023-10-13
 * description  : LikeRepositoryImpl
 */

@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 좋아요 엔티티 조회
     * @param userEntity - 유저 엔티티
     * @param reviewEntity - 리뷰 엔티티
     * @return 좋아요 엔티티
     */
    @Override
    public LikeEntity findByUserAndReview(UserEntity userEntity, ReviewEntity reviewEntity) {
        return queryFactory
                .selectFrom(likeEntity)
                .where(
                        eqUser(userEntity)
                        ,eqReview(reviewEntity)
                ).fetchOne();
    }

    private BooleanExpression eqUser(UserEntity userEntity){
        return userEntity == null ? null : likeEntity.user.eq(userEntity);
    }

    private BooleanExpression eqReview(ReviewEntity reviewEntity){
        return reviewEntity == null ? null : likeEntity.review.eq(reviewEntity);
    }
}
