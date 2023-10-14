package com.sk.namevalue.domain.animal.dto;

import lombok.Getter;

/**
 * title        : 동물 Dto
 * author       : sim
 * date         : 2023-10-14
 * description  : 동물 Dto
 */

@Getter
public class AnimalDto {
    private final Long animalId;
    private final String name;
    private final String emoji;

    private AnimalDto(Long animalId, String name, String emoji){
        this.animalId = animalId;
        this.name = name;
        this.emoji = emoji;
    }

    public AnimalDto of(Long animalId, String name, String emoji){
        return new AnimalDto(animalId, name, emoji);
    }
}
