package com.sk.namevalue.domain.personality.dto;

import lombok.Getter;

/**
 * title        : PersonalityDto
 * author       : sim
 * date         : 2023-10-14
 * description  : PersonalityDto
 */

@Getter
public class PersonalityDto {
    private final Long personalityId;
    private final String name;

    public PersonalityDto(Long personalityId, String name) {
        this.personalityId = personalityId;
        this.name = name;
    }
}
