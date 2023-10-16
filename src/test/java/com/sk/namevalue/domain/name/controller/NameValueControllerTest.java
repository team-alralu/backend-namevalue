package com.sk.namevalue.domain.name.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.service.NameValueService;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.InvalidUserException;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        : NameValueControllerTest
 * author       : sim
 * date         : 2023-10-11
 * description  : NameValueControllerTest
 */

@WebMvcTest(NameValueController.class)
@MockBean(JpaMetamodelMappingContext.class)
class NameValueControllerTest extends TestFixture {

    private MockMvc mockMvc;
    @MockBean
    private NameValueService nameValueService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        mockMvc = MockMvcFactory.of(webApplicationContext, StandardCharsets.UTF_8.name());
    }

    @DisplayName("유효 사용자에 대한 이름 정보 저장")
    @Test
    void saveWithValidUserId() throws Exception {
        mockMvc.perform(
                post("/api/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(NAME_VALUE_SAVE_DTO)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(nameValueService).save(VALID_USER_ID,any(NameValueDto.Save.class));
    }

    @DisplayName("유효하지 않은 사용자에 대한 이름 정보 저장")
    @Test
    void saveWithReg() throws Exception {

        willThrow(new InvalidUserException(ErrorMessage.INVALID_USER))
                .given(nameValueService).save(any(Long.class), any(NameValueDto.Save.class));

        mockMvc.perform(
                        post("/api/name")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(NAME_VALUE_SAVE_DTO)))
                .andExpect(status().isNotFound())
                .andDo(print());

        verify(nameValueService).save(VALID_USER_ID, any(NameValueDto.Save.class));
    }

    @DisplayName("유효 이름에 대한 이름 정보 조회")
    @Test
    void getNameInfoWithValidPersonName() throws Exception{

        mockMvc.perform(
                get("/api/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(VALID_NAME_VALUE_SELECT_DTO)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(nameValueService).getNameInfo(any(NameValueDto.Select.class));
    }

    @DisplayName("유효하지 않은 이름에 대한 이름 정보 조회")
    @Test
    void getNameInfoWithInvalidPersonName() throws Exception{

        given(nameValueService.getNameInfo(any(NameValueDto.Select.class)))
                .willThrow(new DataNotFoundException(ErrorMessage.PERSON_NAME_NOT_FOUND));

        mockMvc.perform(
                        get("/api/name")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(INVALID_NAME_VALUE_SELECT_DTO)))
                .andExpect(status().isNotFound())
                .andDo(print());

        verify(nameValueService).getNameInfo(any(NameValueDto.Select.class));
    }
    @DisplayName("이름 가치 조회")
    @Test
    void test() throws Exception {

        given(nameValueService.getValue(any(ValueDto.Request.class)))
                .willReturn(VALUE_RESPONSE_DTO);

        mockMvc.perform(
                get("/api/name/value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALUE_REQUEST_DTO))
        ).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(VALUE_RESPONSE_DTO)))
                .andDo(print());

        verify(nameValueService).getValue(any(ValueDto.Request.class));
    }
}