package com.sk.namevalue.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

/**
 * title        : MBTI
 * author       : sim
 * date         : 2023-10-15
 * description  : MBTI
 */
public enum MBTI {
    ISTJ, ISFJ, INFJ, INTJ
    ,ISTP, ISFP, INFP, INTP
    ,ESTP, ESFP, ENFP, ENTP
    ,ESTJ, ESFJ, ENFJ, ENTJ
    ,NONE;

    @JsonCreator
    public static MBTI parsing(String input){
        return Stream.of(MBTI.values())
                .filter(mbti -> mbti.toString().equals(input.toString()))
                .findFirst()
                .orElse(null);
    }
}
