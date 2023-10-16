package com.sk.namevalue.domain.name.repository.querydsl;

import com.sk.namevalue.domain.name.dto.ValueDto;

/**
 * title        : PersonNameRepositoryCustom
 * author       : sim
 * date         : 2023-10-12
 * description  : PersonNameRepositoryCustom
 */
public interface PersonNameRepositoryCustom {
    ValueDto.Response findValueByLikeabilityPoint(int point);
}
