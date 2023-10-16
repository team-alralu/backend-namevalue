package com.sk.namevalue.domain.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.domain.model.enums.Token;
import com.sk.namevalue.domain.token.controller.TokenController;
import com.sk.namevalue.domain.token.service.TokenService;
import com.sk.namevalue.global.exception.JwtTokenException;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.sk.namevalue.config.fixture.TestFixture.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        : TokenControllerTest
 * author       : sim
 * date         : 2023-09-18
 * description  : TokenControllerTest
 */

@WebMvcTest(TokenController.class)
@MockBean(JpaMetamodelMappingContext.class)
class TokenControllerTest {

    @MockBean
    private TokenService tokenService;
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(encodingFilter)
                .build();

        given(tokenService.renewToken(VALID_REFRESH_TOKEN)).willReturn(RENEW_TOKEN_DTO);
        given(tokenService.renewToken(INVALID_REFRESH_TOKEN)).willThrow(new JwtTokenException("토큰이 만료되었습니다. 다시 로그인하세요."));
    }

    @DisplayName("유효 Refresh Token을 통한 AccessToken 갱신")
    @Test
    void renewAccessTokenWithValidRefreshToken() throws Exception {
        mockMvc.perform(
                post("/api/token")
                        .cookie(new Cookie(Token.REFRESH_TOKEN.getKey(), VALID_REFRESH_TOKEN))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(RENEW_TOKEN_DTO)))
                .andDo(print());
    }

    @DisplayName("유효하지 않은 Refresh Token을 통한 AccessToken 갱신")
    @ParameterizedTest
    @ValueSource(strings = { INVALID_REFRESH_TOKEN, ""})
    void renewAccessTokenWithInalidRefreshToken(String token) throws Exception {
        mockMvc.perform(
                        post("/api/token")
                                .cookie(new Cookie(Token.REFRESH_TOKEN.getKey(), token))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}