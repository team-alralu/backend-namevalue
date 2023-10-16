package com.sk.namevalue.domain.token.service;

import com.sk.namevalue.domain.token.dto.TokenDto;
import com.sk.namevalue.global.auth.JwtProvider;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.JwtTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sk.namevalue.config.fixture.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * title        : TokenServiceTest
 * author       : sim
 * date         : 2023-10-17
 * description  : TokenServiceTest
 */

class TokenServiceTest {

    private final JwtProvider jwtProvider = mock(JwtProvider.class);
    private final RedisService redisService = mock(RedisService.class);
    private final TokenService tokenService = new TokenService(jwtProvider, redisService);
    private static final String BEARER = "bearer ";

    @DisplayName("유효 리프레시 토큰을 통한 액세스 토큰 갱신")
    @Test
    public void renewAccessTokenWithValidRefreshToken(){
        given(jwtProvider.extractJwtToken(BEARER+VALID_REFRESH_TOKEN))
                .willReturn(VALID_REFRESH_TOKEN);

        given(jwtProvider.parseJwtToken(VALID_REFRESH_TOKEN))
                .willReturn(VALID_CLAIMS);

        given(jwtProvider.generateAccessToken(VALID_CLAIMS))
                .willReturn(RENEW_ACCESS_TOKEN);

        given(jwtProvider.generateRefreshToken(VALID_CLAIMS))
                .willReturn(RENEW_REFRESH_TOKEN);

        given(jwtProvider.extractIdFromClaims(VALID_CLAIMS))
                .willReturn(VALID_USER_ID);

        TokenDto tokenDto = tokenService.renewToken(BEARER+VALID_REFRESH_TOKEN);
        assertThat(tokenDto.getAccessToken()).isEqualTo(RENEW_ACCESS_TOKEN);
    }

    @DisplayName("만료된 리프레시 토큰을 통한 액세스 토큰 갱신")
    @Test
    public void renewAccessTokenWithInvalidRefreshToken(){
        given(jwtProvider.extractJwtToken(BEARER+INVALID_REFRESH_TOKEN))
                .willReturn(INVALID_REFRESH_TOKEN);

        given(jwtProvider.parseJwtToken(INVALID_REFRESH_TOKEN))
                .willThrow(new JwtTokenException(ErrorMessage.EXPIRED_JWT_TOKEN));

        assertThatThrownBy(() -> tokenService.renewToken(BEARER+INVALID_REFRESH_TOKEN))
                .isInstanceOf(JwtTokenException.class);
    }
}