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
    private final String path;

    // QueryDSL Projections.constructor 는 private 접근제어자에 대해서는 접근이 불가능함. public 으로 수정
    public PersonalityDto(Long personalityId, String name, String path) {
        this.personalityId = personalityId;
        this.name = name;
        this.path = path;
    }

    public static PersonalityDto of(Long personalityId, String name, String path){
        return new PersonalityDto(personalityId, name, path);
    }
}
