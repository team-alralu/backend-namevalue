package com.sk.namevalue.domain.like.controller;

import com.sk.namevalue.config.MockMvcFactory;
import com.sk.namevalue.config.TestUser;
import com.sk.namevalue.config.TestUserType;
import com.sk.namevalue.domain.like.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-13
 * description  :
 */

@WebMvcTest(LikeController.class)
@MockBean(JpaMetamodelMappingContext.class)
class LikeControllerTest {

    @MockBean
    private LikeService likeService;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext){
        mockMvc = MockMvcFactory.of(webApplicationContext, StandardCharsets.UTF_8.name());
    }
    @Test
    @TestUser(TestUserType.VALID)
    void like() throws Exception {
        mockMvc.perform(
                post("/api/like/{reviewId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(likeService).like(TestUserType.VALID.getLoginId(), 1L);
    }
}