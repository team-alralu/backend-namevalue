package com.sk.namevalue.domain.likeability.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.sk.namevalue.domain.likeability.entity.QLikeabilityEntity.likeabilityEntity;

/**
 * title        : LikeabilityRepositoryImpl
 * author       : sim
 * date         : 2023-10-16
 * description  : LikeabilityRepositoryImpl
 */

@RequiredArgsConstructor
public class LikeabilityRepositoryImpl implements LikeabilityRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 이름에 대한 평균 호감도 조회
     * @param personName - 이름
     * @return 평균 호감도
     */
    @Override
    public int findAvgPointByPersonName(String personName) {

        // personName에 대한 값이 없을 시 평균 값으로 0을 반환한다. NPE가 발생하지 않음을 보장한다.
        @SuppressWarnings("null")
        int result =  queryFactory.select(
                likeabilityEntity.point.avg())
                .from(likeabilityEntity)
                .where(
                        eqPersonName(personName))
                .fetchOne()
                .intValue();

        return result;
    }

    private BooleanExpression eqPersonName(String personName){
        return personName == null ? null : likeabilityEntity.personName.personName.eq(personName);
    }
}
