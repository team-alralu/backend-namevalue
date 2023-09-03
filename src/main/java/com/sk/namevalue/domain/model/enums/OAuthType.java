package com.sk.namevalue.domain.model.enums;

import lombok.Getter;

/**
 * title        : 소셜로그인 타입
 * author       : tlatmsrud
 * date         : 2023-08-28
 * description  : 소셜로그인 타입 열거형 클래스
 */

@Getter
public enum OAuthType {

    KAKAO("kakao")
    , NAVER("naver");

    private final String value;

    OAuthType(String value){
        this.value = value;
    }
}
