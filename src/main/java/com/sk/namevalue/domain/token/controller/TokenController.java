package com.sk.namevalue.domain.token.controller;

import com.sk.namevalue.domain.token.dto.TokenDto;
import com.sk.namevalue.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * title        : TokenController
 * author       : sim
 * date         : 2023-09-18
 * description  : TokenController
 */

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    /**
     * JWT token 갱신 갱신
     * @param refreshToken - Cookie에서 추출한 refreshToken
     * @return TokenDto
     */
    @PostMapping
    public TokenDto renewToken(@RequestHeader(value = "Authorization") String refreshToken){
        return tokenService.renewToken(refreshToken);
    }
}
