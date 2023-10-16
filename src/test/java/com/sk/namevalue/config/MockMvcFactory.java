package com.sk.namevalue.config;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


/**
 * title        : MockMvc 팩터리 클래스
 * author       : sim
 * date         : 2023-10-12
 * description  : MockMvc 팩터리 클래스
 */
public class MockMvcFactory {

    public static MockMvc of(WebApplicationContext webApplicationContext, String charset){
        return MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(createCharacterEncodingFilter(charset))
                .build();
    }

    private static CharacterEncodingFilter createCharacterEncodingFilter(String charset){
        return new CharacterEncodingFilter(charset, true);
    }
}
