package com.sk.namevalue.domain.personality.repository.querydsl;

import com.sk.namevalue.domain.personality.dto.PersonalityDto;

/**
 * title        : PersonalityRepositoryCustom
 * author       : sim
 * date         : 2023-10-15
 * description  : PersonalityRepositoryCustom
 */
public interface PersonalityRepositoryCustom {
    PersonalityDto findTopByPersonNameOrderByCount(String personName);
}
