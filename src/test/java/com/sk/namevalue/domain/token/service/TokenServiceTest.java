package com.sk.namevalue.domain.token.service;

import com.sk.namevalue.domain.token.dto.TokenDto;
import com.sk.namevalue.global.auth.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.sk.namevalue.config.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * title        : TokenServiceTest
 * author       : sim
 * date         : 2023-09-20
 * description  : TokenServiceTest
 */

class TokenServiceTest {

    private final JwtProvider jwtProvider = mock(JwtProvider.class);
    private final RedisService redisService = mock(RedisService.class);
    private final TokenService tokenService = new TokenService(jwtProvider, redisService);

    @BeforeEach
    void setUp(){
        given(jwtProvider.parseJwtToken(VALID_REFRESH_TOKEN)).willReturn(VALID_CLAIMS);
        given(jwtProvider.generateAccessToken(VALID_CLAIMS)).willReturn(RENEW_ACCESS_TOKEN);
        given(jwtProvider.generateRefreshToken(VALID_CLAIMS)).willReturn(RENEW_REFRESH_TOKEN);
        given(jwtProvider.extractIdFromClaims(VALID_CLAIMS)).willReturn(VALID_USERID);
    }
    @Test
    public void JWT_토큰_갱신하기(){
        TokenDto tokenDto = tokenService.renewToken(VALID_REFRESH_TOKEN);
        assertThat(tokenDto.getAccessToken()).isEqualTo(RENEW_ACCESS_TOKEN);
    }
}