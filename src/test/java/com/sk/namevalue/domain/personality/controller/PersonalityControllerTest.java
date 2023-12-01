package com.sk.namevalue.domain.personality.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.personality.service.PersonalityService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        :
 * author       : sim
 * date         : 2023-12-01
 * description  :
 */

@WebMvcTest(PersonalityController.class)
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(RestDocumentationExtension.class)
class PersonalityControllerTest extends TestFixture {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private PersonalityService personalityService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcFactory.of(webApplicationContext, restDocumentation, StandardCharsets.UTF_8.name());
    }

    @Test
    @DisplayName("성격 리스트 조회")
    void getPersonalityList() throws Exception {

        given(personalityService.getPersonalityList()).willReturn(PERSONALITY_DTO_LIST);

        mockMvc.perform(
                        get("/api/personality/list")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header(AUTHORIZATION, JWT_TOKEN)
                ).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(PERSONALITY_DTO_LIST)))
                .andDo(print())
                .andDo(document("getPersonalityList"
                        ,responseFields(
                            fieldWithPath("[].personalityId").description("성격 ID").type(Integer.class)
                            ,fieldWithPath("[].name").description("이름").type(String.class)
                            ,fieldWithPath("[].path").description("이미지 경로").type(String.class))
                        )
                );

    }
}

