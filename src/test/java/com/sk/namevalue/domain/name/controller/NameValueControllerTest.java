package com.sk.namevalue.domain.name.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.service.NameValueService;
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

import static com.sk.namevalue.config.TestFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        : NameValueControllerTest
 * author       : sim
 * date         : 2023-10-11
 * description  : NameValueControllerTest
 */

@WebMvcTest(NameValueController.class)
@MockBean(JpaMetamodelMappingContext.class)
class NameValueControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private NameValueService nameValueService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        mockMvc = MockMvcFactory.of(webApplicationContext, StandardCharsets.UTF_8.name());
    }

    @DisplayName("네임벨류 저장하기")
    @Test
    void save() throws Exception {
        mockMvc.perform(
                post("/api/name")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(NAME_VALUE_DTO)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(nameValueService).save(any(NameValueDto.class));
    }
}