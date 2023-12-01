package com.sk.namevalue.domain.personality.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import lombok.RequiredArgsConstructor;

import static com.sk.namevalue.domain.name.entity.QPersonNamePersonalityEntity.personNamePersonalityEntity;
import static com.sk.namevalue.domain.personality.domain.QPersonalityEntity.personalityEntity;

/**
 * title        : PersonalityRepositoryImpl
 * author       : sim
 * date         : 2023-10-15
 * description  : PersonalityRepositoryImpl
 */

@RequiredArgsConstructor
public class PersonalityRepositoryImpl implements PersonalityRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 이름에 대한 대표 성격 조회
     * @param personName - 사람 이름
     * @return 대표 성격 Dto
     */
    @Override
    public PersonalityDto findTopByPersonNameOrderByCount(String personName) {

        Long representPersonalityId = queryFactory.select(
                        personNamePersonalityEntity.personality.personalityId)
                .from(personNamePersonalityEntity)
                .groupBy(personNamePersonalityEntity.personality.personalityId)
                .orderBy(personNamePersonalityEntity.personality.personalityId.count().desc())
                .fetchFirst();

        return queryFactory.select(
                Projections.constructor(
                        PersonalityDto.class
                        , personalityEntity.personalityId
                        , personalityEntity.name
                        , personalityEntity.path
                ))
                .from(personalityEntity)
                .where(personalityEntity.personalityId.eq(representPersonalityId))
                .fetchOne();
    }

    private BooleanExpression eqPersonName(String personName){
        return personName == null ? null : personNamePersonalityEntity.personName.personName.eq(personName);
    }
}
