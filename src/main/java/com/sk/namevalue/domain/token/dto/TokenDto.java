package com.sk.namevalue.domain.token.dto;

import lombok.Getter;

/**
 * title        : TokenDto
 * author       : sim
 * date         : 2023-09-18
 * description  : Token DTO
 */

@Getter
public class TokenDto {
    private final String accessToken;

    public TokenDto(String accessToken){
        this.accessToken = accessToken;
    }
}
