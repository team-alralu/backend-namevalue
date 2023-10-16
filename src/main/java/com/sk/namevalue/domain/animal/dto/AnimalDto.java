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

    public AnimalDto(Long animalId, String name){
        this.animalId = animalId;
        this.name = name;
    }

}
