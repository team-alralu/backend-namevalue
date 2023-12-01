package com.sk.namevalue.config.fixture;

import com.sk.namevalue.domain.model.enums.MBTI;
import com.sk.namevalue.domain.model.enums.OAuthType;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.review.dto.ReviewDto;
import com.sk.namevalue.domain.token.dto.TokenDto;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.domain.user.dto.RequiredInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * title        : TestFixture
 * author       : sim
 * date         : 2023-09-06
 * description  : Test Fixture 관리 인터페이스
 */
public class TestFixture {
    public static final String AUTHORIZATION = "Authorization";
    public static final String JWT_TOKEN = "Bearer JWT_TOKEN";
    public static final Long VALID_USER_ID = 1L;
    public static final UserEntity VALID_USER_ENTITY = UserEntity.of("valid@naver.com", "홍길동", OAuthType.NAVER);
    public static final Long INVALID_USER_ID = 100L;
    public static final String VALID_REFRESH_TOKEN = "VALID_REFRESH_TOKEN";
    public static final String RENEW_REFRESH_TOKEN = "RENEW_REFRESH_TOKEN";
    public static final String INVALID_REFRESH_TOKEN = "INVALID_REFRESH_TOKEN";
    public static final String RENEW_ACCESS_TOKEN = "RENEW_ACCESS_TOKEN";
    public static final Claims VALID_CLAIMS = new DefaultClaims(Map.of());
    public static final TokenDto RENEW_TOKEN_DTO = TokenDto.from(RENEW_ACCESS_TOKEN);
    public static final NameValueDto.Save NAME_VALUE_SAVE_DTO = new NameValueDto.Save("홍길동","성격이 온순하고 바른 사람입니다.",
            List.of(1L, 2L, 3L), 98);
    public static final String VALID_PERSON_NAME = "홍길동";
    public static final Long REPRESENT_PERSONALITY_ID_OF_VALID_PERSON_NAME = 2L;
    public static final NameValueDto.Select VALID_NAME_VALUE_SELECT_DTO = new NameValueDto.Select(VALID_PERSON_NAME);
    public static final PersonNameEntity VALID_PERSON_NAME_ENTITY = PersonNameEntity.from(VALID_PERSON_NAME);
    public static final String INVALID_PERSON_NAME = "제네럴93";
    public static final NameValueDto.Select INVALID_NAME_VALUE_SELECT_DTO = new NameValueDto.Select(INVALID_PERSON_NAME);
    public static final ValueDto.Request VALUE_REQUEST_DTO = new ValueDto.Request(VALID_PERSON_NAME);
    public static final ValueDto.Response VALUE_RESPONSE_DTO = new ValueDto.Response("돌멩이", 4);

    public static final List<ReviewDto> TOP_REVIEW_DTO_LIST = List.of(
            new ReviewDto(100L, "리뷰 100", 10)
            ,new ReviewDto(200L, "리뷰 200", 9)
            ,new ReviewDto(300L, "리뷰 300", 8)
            ,new ReviewDto(400L, "리뷰 400", 7)
            ,new ReviewDto(500L, "리뷰 500", 6)
    );

    public static final List<ReviewDto> REVIEW_DTO_LIST = List.of(
            new ReviewDto(600L, "리뷰 600", 0)
            ,new ReviewDto(599L, "리뷰 599", 0)
            ,new ReviewDto(598L, "리뷰 598", 0)
            ,new ReviewDto(597L, "리뷰 597", 0)
            ,new ReviewDto(596L, "리뷰 596", 1)
            ,new ReviewDto(595L, "리뷰 595", 0)
            ,new ReviewDto(594L, "리뷰 594", 0)
    );

    public static final PersonalityDto REPRESENT_PERSONALITY_DTO = PersonalityDto.of(1L, "착한", "/personality/Kind.png");
    public static final RequiredInfoDto.Request REQUIRED_INFO_REQUEST_DTO = new RequiredInfoDto.Request(MBTI.ENFJ, NAME_VALUE_SAVE_DTO);
    public static final NameValueDto.Response NAME_VALUE_RESPONSE_DTO = new NameValueDto.Response(TOP_REVIEW_DTO_LIST,REVIEW_DTO_LIST,REPRESENT_PERSONALITY_DTO);
    public static final List<PersonalityDto> PERSONALITY_DTO_LIST = Arrays.asList(
            PersonalityDto.of (1L, "다혈질인", "/images/personality/Volatile.png"),
            PersonalityDto.of (2L, "걱정많은", "/images/personality/Worrywart.png"),
            PersonalityDto.of (3L, "재밌는", "/images/personality/Funny.png"),
            PersonalityDto.of (4L, "호기심많은", "/images/personality/Curious/png"),
            PersonalityDto.of (5L, "신중한", "/images/personality/Discreet.png"),
            PersonalityDto.of (6L, "이기적인", "/images/personality/Selfish.png"),
            PersonalityDto.of (7L, "내성적인", "/images/personality/Introverted.png"),
            PersonalityDto.of (8L, "눈치없는", "/images/personality/Tacless.png"),
            PersonalityDto.of (9L, "소심한", "/images/personality/Timid.png"),
            PersonalityDto.of (10L, "다정한", "/images/personality/Affectionate.png"),
            PersonalityDto.of (11L, "순수한", "/images/personality/Innocent.png"),
            PersonalityDto.of (12L, "게으른", "/images/personality/Lazy.png"),
            PersonalityDto.of (13L, "무관심한", "/images/personality/Apathetic.png"),
            PersonalityDto.of (14L, "매력적인", "/images/personality/Charming.png"),
            PersonalityDto.of (15L, "친절한", "/images/personality/Kind.png"),
            PersonalityDto.of (16L, "너그러운", "/images/personality/Generous.png")
    );
}
