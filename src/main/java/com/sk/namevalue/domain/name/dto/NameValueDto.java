package com.sk.namevalue.domain.name.dto;

import lombok.Getter;

import java.util.List;

/**
 * title        : 네임벨류 Dto
 * author       : sim
 * date         : 2023-10-11
 * description  : 네임벨류 Dto
 */

@Getter
public class NameValueDto {

    private final String personName;
    private final String review;
    private final List<Long> favoriteList;
    private final List<Long> animalList;
    private final List<Long> personalityList;

    private NameValueDto(String personName, String review, List<Long> favoriteList, List<Long> animalList, List<Long> personalityList){
        this.personName = personName;
        this.review = review;
        this.favoriteList = favoriteList;
        this.animalList = animalList;
        this.personalityList = personalityList;
    }

    public static NameValueDto of(String personName, String review, List<Long> favoriteList, List<Long> animalList, List<Long> personalityList) {
        return new NameValueDto(personName, review, favoriteList, animalList, personalityList);
    }
}

