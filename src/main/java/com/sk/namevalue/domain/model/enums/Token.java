package com.sk.namevalue.domain.model.enums;

import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-09-18
 * description  :
 */

@Getter
public enum Token {
    ACCESS_TOKEN("accessToken", 30)
    ,REFRESH_TOKEN("refreshToken", 60*24);

    private final String key;
    private final int maxAge;

    Token(String key, int maxAge){
        this.key = key;
        this.maxAge = maxAge;
    }
}
