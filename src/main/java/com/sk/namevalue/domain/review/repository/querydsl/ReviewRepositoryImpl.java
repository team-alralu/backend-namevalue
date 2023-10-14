package com.sk.namevalue.domain.review.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.domain.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.sk.namevalue.domain.review.entity.QReviewEntity.reviewEntity;

/**
 * title        : ReviewRepositoryImpl
 * author       : sim
 * date         : 2023-10-14
 * description  : ReviewRepositoryImpl
 */

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    /**
     * 좋아요, 등록날짜순으로 정렬된 상위 5개 리뷰 조회
     * @param personName - 이름
     * @return - 상위 5개의 리뷰 Dto 리스트
     */
    @Override
    public List<ReviewDto> findTop5ByPersonNameOrderByLikeCountAndCreateDateDesc(String personName) {

        return queryFactory
                .select(
                Projections.constructor(
                        ReviewDto.class
                        , reviewEntity.reviewId
                        , reviewEntity.content
                        , reviewEntity.likeList.size())
                ).from(reviewEntity)
                .where(
                        eqPersonName(personName))
                .orderBy(
                        reviewEntity.likeList.size().desc()
                        ,reviewEntity.createDate.desc()
                ).fetch();
    }

    /**
     * 등록날짜순으로 정렬된 전체 리뷰 조회
     * @param personName - 사람 이름
     * @return 전체 리뷰 Dto 리스트
     */
    @Override
    public List<ReviewDto> findByPersonNameOrderByCreateDateDesc(String personName) {
        return queryFactory
                .select(
                        Projections.constructor(
                                ReviewDto.class
                                ,reviewEntity.reviewId
                                ,reviewEntity.content
                                ,reviewEntity.likeList.size())
                ).from(reviewEntity)
                .where(
                        eqPersonName(personName)
                ).orderBy(
                        reviewEntity.createDate.desc()
                ).fetch();
    }

    private BooleanExpression eqPersonName(String personName){
        return personName == null ? null : reviewEntity.personName.personName.eq(personName);
    }
}
