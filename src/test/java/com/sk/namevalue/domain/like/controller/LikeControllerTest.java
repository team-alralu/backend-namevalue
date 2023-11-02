package com.sk.namevalue.domain.like.controller;

import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.config.TestUser;
import com.sk.namevalue.config.TestUserType;
import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.like.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-13
 * description  :
 */

@WebMvcTest(LikeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(RestDocumentationExtension.class)
class LikeControllerTest extends TestFixture {

    @MockBean
    private LikeService likeService;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation){
        mockMvc = MockMvcFactory.of(webApplicationContext, restDocumentation, StandardCharsets.UTF_8.name());
    }
    @Test
    @TestUser(TestUserType.VALID)
    void like() throws Exception {
        mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/api/like/{reviewId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print())
                        .andDo(document("like"
                        ,pathParameters(
                                parameterWithName("reviewId").description("리뷰 ID")
                                )));

        verify(likeService).like(eq(VALID_USER_ID), eq(1L));
    }
}