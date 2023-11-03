package com.sk.namevalue.domain.animal.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.domain.animal.dto.AnimalDto;
import lombok.RequiredArgsConstructor;

import static com.sk.namevalue.domain.name.entity.QPersonNameAnimalEntity.personNameAnimalEntity;


/**
 * title        : AnimalRepositoryImpl
 * author       : sim
 * date         : 2023-10-16
 * description  : AnimalRepositoryImpl
 */

@RequiredArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 이름에 대해 좋아요가 가장 많은 동물 정보 조회
     * @param personName - 이름
     * @return 좋아요가 가장 많은 동물 Dto
     */
    @Override
    public AnimalDto findTopByPersonNameOrderByCount(String personName) {
        return queryFactory.select(
                Projections.constructor(
                        AnimalDto.class
                        , personNameAnimalEntity.animal.animalId
                        , personNameAnimalEntity.animal.name
                ))
                .from(personNameAnimalEntity)
                .where(
                        eqPersonName(personName))
                .orderBy(
                        personNameAnimalEntity.animal.animalId.count().desc())
                .fetchFirst();
    }


    private BooleanExpression eqPersonName(String personName){
        return personName == null ? null : personNameAnimalEntity.personName.personName.eq(personName);
    }
}
