package com.sk.namevalue.domain.animal.repository.querydsl;

import com.sk.namevalue.domain.animal.dto.AnimalDto;

/**
 * title        : AnimalRepositoryCustom
 * author       : sim
 * date         : 2023-10-16
 * description  : AnimalRepositoryCustom
 */
public interface AnimalRepositoryCustom {
    AnimalDto findTopByPersonNameOrderByCount(String personName);
}
