package com.sk.namevalue.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * title        : Spring Security Config
 * author       : sim
 * date         : 2023-08-31
 * description  : 스프링 시큐리티 설정
 */

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((authz -> authz
                        .requestMatchers("/api/**").hasRole("USER")
                        .anyRequest().authenticated())
                )
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .oauth2Login();

        return http.build();
    }
}
