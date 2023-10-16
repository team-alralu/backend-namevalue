package com.sk.namevalue.domain.name.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;

import static com.sk.namevalue.domain.name.entity.QValueEntity.valueEntity;

/**
 * title        : PersonNameRepositoryImpl
 * author       : sim
 * date         : 2023-10-12
 * description  : PersonNameRepositoryImpl
 */

@RequiredArgsConstructor
public class PersonNameRepositoryImpl implements PersonNameRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 점수에 대한 가치 조회
     * @param point - 점수
     * @return 가치 Dto
     */
    @Override
    public ValueDto.Response findValueByLikeabilityPoint(int point) {

        String name = queryFactory
                .select(
                    valueEntity.name)
                .from(valueEntity)
                .where(
                        valueEntity.min.loe(point)
                        ,valueEntity.max.goe(point))
                .fetchOne();

        if(name == null){
            throw new DataNotFoundException(ErrorMessage.INVALID_VALUE_OF_POINT);
        }
        return new ValueDto.Response(name, point);
    }

}
