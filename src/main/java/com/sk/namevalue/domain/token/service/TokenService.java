package com.sk.namevalue.domain.token.service;

import com.sk.namevalue.domain.model.enums.Token;
import com.sk.namevalue.domain.token.dto.TokenDto;
import com.sk.namevalue.global.auth.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * title        : TokenService
 * author       : sim
 * date         : 2023-09-18
 * description  : JWT 서비스 클래스
 */

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtProvider jwtProvider;

    private final RedisService redisService;

    /**
     * JWT 토큰 갱신
     * @param refreshToken - JWT refresh token
     * @return 갱신된 accessToken 에 대한 TokenDto 인스턴스
     */
    public TokenDto renewToken(String refreshToken){

        Claims claims = jwtProvider.parseJwtToken(refreshToken);
        String renewAccessToken = jwtProvider.generateAccessToken(claims);
        String renewRefreshToken = jwtProvider.generateRefreshToken(claims);

        Long userId = jwtProvider.extractIdFromClaims(claims);
        redisService.save(userId, renewRefreshToken, Token.REFRESH_TOKEN.getMaxAge(), TimeUnit.MINUTES);

        return new TokenDto(renewAccessToken);
    }

}
