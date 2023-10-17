package com.sk.namevalue.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.config.TestUser;
import com.sk.namevalue.config.TestUserType;
import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.user.dto.RequiredInfoDto;
import com.sk.namevalue.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        : UserControllerTest
 * author       : sim
 * date         : 2023-10-18
 * description  : UserControllerTest
 */

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest extends TestFixture {

    @MockBean
    private UserService userService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        this.mockMvc = MockMvcFactory.of(webApplicationContext, StandardCharsets.UTF_8.name());
    }

    @DisplayName("필수 정보 등록")
    @Test
    @TestUser(TestUserType.VALID)
    void regRequiredInfo() throws Exception {

        mockMvc.perform(
                post("/api/user/required")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(REQUIRED_INFO_REQUEST_DTO))
                ).andExpect(status().isNoContent())
                .andDo(print());

        verify(userService).regRequiredInfo(any(Long.class), any(RequiredInfoDto.Request.class));
    }
}