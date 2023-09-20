package com.sk.namevalue.global.config;

import com.sk.namevalue.global.auth.JwtArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * title        : WebMvcConfig
 * author       : sim
 * date         : 2023-09-20
 * description  : MVC 설정 클래스
 */

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final JwtArgumentResolver jwtArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtArgumentResolver);
    }
}
