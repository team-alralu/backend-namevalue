package com.sk.namevalue.domain.likeability.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
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

        Double result = queryFactory.select(
                likeabilityEntity.point.avg())
                .from(likeabilityEntity)
                .where(
                        eqPersonName(personName))
                .fetchOne();

        if(result == null){
            throw new DataNotFoundException(ErrorMessage.ZERO_LIKEABILITY);
        }
        return result.intValue();
    }

    private BooleanExpression eqPersonName(String personName){
        return personName == null ? null : likeabilityEntity.personName.personName.eq(personName);
    }
}
