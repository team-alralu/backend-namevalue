package com.sk.namevalue.config;

import com.sk.namevalue.domain.token.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import java.util.Map;

/**
 * title        : TestFixture
 * author       : sim
 * date         : 2023-09-06
 * description  : Test Fixture 관리 인터페이스
 */
public interface TestFixture {
    Long VALID_USERID = 1L;
    String VALID_EMAIL = "valid@naver.com";
    String VALID_REFRESH_TOKEN = "VALID_REFRESH_TOKEN";
    String RENEW_REFRESH_TOKEN = "RENEW_REFRESH_TOKEN";
    String INVALID_REFRESH_TOKEN = "INVALID_REFRESH_TOKEN";
    String RENEW_ACCESS_TOKEN = "RENEW_ACCESS_TOKEN";
    Claims VALID_CLAIMS = new DefaultClaims(Map.of());

    TokenDto RENEW_TOKEN_DTO = TokenDto.from(RENEW_ACCESS_TOKEN);

}
