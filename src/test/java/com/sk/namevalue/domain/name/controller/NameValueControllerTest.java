package com.sk.namevalue.domain.name.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.config.TestUser;
import com.sk.namevalue.config.TestUserType;
import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.service.NameValueService;
import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.InvalidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
@ExtendWith(RestDocumentationExtension.class)
class NameValueControllerTest extends TestFixture {

    private MockMvc mockMvc;
    @MockBean
    private NameValueService nameValueService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation){
        mockMvc = MockMvcFactory.of(webApplicationContext, restDocumentation, StandardCharsets.UTF_8.name());
    }

    @DisplayName("유효 사용자에 대한 이름 정보 저장")
    @Test
    @TestUser(TestUserType.VALID)
    void saveWithValidUserId() throws Exception {
        mockMvc.perform(
                post("/api/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(NAME_VALUE_SAVE_DTO))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print())
                        .andDo(document("save"
                        ,requestFields(
                                fieldWithPath("personName").description("이름").type(String.class)
                                        ,fieldWithPath("review").description("리뷰").type(String.class)
                                        ,fieldWithPath("personalityList").description("성격 리스트 (최대 3개)").type(Integer[].class)
                                        ,fieldWithPath("likeability").description("호감도").type(Integer.class)
                                )));
    }

    @DisplayName("유효하지 않은 사용자에 대한 이름 정보 저장")
    @Test
    @TestUser(TestUserType.VALID)
    void saveWithReg() throws Exception {

        willThrow(new InvalidUserException(ErrorMessage.INVALID_USER))
                .given(nameValueService).save(any(Long.class), any(NameValueDto.Save.class));

        mockMvc.perform(
                        post("/api/name")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(NAME_VALUE_SAVE_DTO)))
                .andExpect(status().isNotFound())
                .andDo(print());

        verify(nameValueService).save(eq(VALID_USER_ID), any(NameValueDto.Save.class));
    }

    @DisplayName("유효 이름에 대한 이름 정보 조회")
    @Test
    void getNameInfoWithValidPersonName() throws Exception{

        given(nameValueService.getNameInfo(any(NameValueDto.Select.class)))
                .willReturn(NAME_VALUE_RESPONSE_DTO);

        mockMvc.perform(
                get("/api/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(VALID_NAME_VALUE_SELECT_DTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(NAME_VALUE_RESPONSE_DTO)))
                .andDo(print())
                        .andDo(document("getNameInfo"
                        ,requestFields(
                                fieldWithPath("personName").description("이름").type(String.class))
                        ,responseFields(
                                fieldWithPath("topReviewList").description("좋아요 순 상위 리뷰 리스트").type(List.class)
                                        ,fieldWithPath("topReviewList[].reviewId").description("리뷰 ID").type(Long.class)
                                        ,fieldWithPath("topReviewList[].review").description("리뷰 내용").type(String.class)
                                        ,fieldWithPath("topReviewList[].likeCnt").description("좋아요 수").type(Integer.class)
                                ,fieldWithPath("reviewList").description("리뷰 리스트").type(List.class)
                                        ,fieldWithPath("reviewList[].reviewId").description("리뷰 ID").type(Long.class)
                                        ,fieldWithPath("reviewList[].review").description("리뷰 내용").type(String.class)
                                        ,fieldWithPath("reviewList[].likeCnt").description("좋아요 수").type(Integer.class)
                                ,fieldWithPath("representPersonality").description("대표 성격").type(PersonalityDto.class)
                                        ,fieldWithPath("representPersonality.personalityId").description("성격 ID").type(Long.class)
                                        ,fieldWithPath("representPersonality.name").description("성격 명").type(String.class)
                                        ,fieldWithPath("representPersonality.path").description("경로").type(String.class)
                                )));

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