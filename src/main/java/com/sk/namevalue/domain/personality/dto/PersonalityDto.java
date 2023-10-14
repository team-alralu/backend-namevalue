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
    private final String emoji;

    private PersonalityDto(Long personalityId, String name, String emoji) {
        this.personalityId = personalityId;
        this.name = name;
        this.emoji = emoji;
    }

    public PersonalityDto of(Long personalityId, String name, String emoji){
        return new PersonalityDto(personalityId, name, emoji);
    }
}
