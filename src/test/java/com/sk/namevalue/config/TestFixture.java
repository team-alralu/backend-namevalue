package com.sk.namevalue.config;

import com.sk.namevalue.domain.name.domain.PersonNameEntity;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.token.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.List;
import java.util.Map;

/**
 * title        : TestFixture
 * author       : sim
 * date         : 2023-09-06
 * description  : Test Fixture 관리 인터페이스
 */
public class TestFixture {
    public static final Long VALID_USERID = 1L;
    public static final String VALID_EMAIL = "valid@naver.com";
    public static final String VALID_REFRESH_TOKEN = "VALID_REFRESH_TOKEN";
    public static final String RENEW_REFRESH_TOKEN = "RENEW_REFRESH_TOKEN";
    public static final String INVALID_REFRESH_TOKEN = "INVALID_REFRESH_TOKEN";
    public static final String RENEW_ACCESS_TOKEN = "RENEW_ACCESS_TOKEN";
    public static final Claims VALID_CLAIMS = new DefaultClaims(Map.of());
    public static final TokenDto RENEW_TOKEN_DTO = TokenDto.from(RENEW_ACCESS_TOKEN);
    public static final NameValueDto NAME_VALUE_DTO = NameValueDto.of("홍길동","성격이 온순하고 바른 사람입니다.",
            List.of(1L, 2L, 3L), List.of(1L, 2L,3L), List.of(1L, 2L, 3L));
    public static final PersonNameEntity PERSON_NAME_ENTITY = PersonNameEntity.from("홍길동");

}
