package com.sk.namevalue.domain.token;

import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.domain.token.controller.TokenController;
import com.sk.namevalue.domain.token.service.TokenService;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.JwtTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static com.sk.namevalue.config.fixture.TestFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    private static final String BEARER = "bearer ";
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        this.mockMvc = MockMvcFactory.of(webApplicationContext, StandardCharsets.UTF_8.name());
    }

    @DisplayName("유효 Refresh Token을 통한 AccessToken 갱신")
    @Test
    void renewAccessTokenWithValidRefreshToken() throws Exception {

        given(tokenService.renewToken(any(String.class)))
                .willReturn(RENEW_TOKEN_DTO);

        mockMvc.perform(
                post("/api/token")
                        .header("Authorization", BEARER + VALID_REFRESH_TOKEN))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("유효하지 않은 Refresh Token을 통한 AccessToken 갱신")
    @Test
    void renewAccessTokenWithInalidRefreshToken() throws Exception {
        given(tokenService.renewToken(any(String.class)))
                .willThrow(new JwtTokenException(ErrorMessage.EXPIRED_JWT_TOKEN));

        mockMvc.perform(
                post("/api/token")
                                .header("Authorization", BEARER + INVALID_REFRESH_TOKEN))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}