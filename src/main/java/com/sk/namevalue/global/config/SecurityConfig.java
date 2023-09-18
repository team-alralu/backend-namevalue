package com.sk.namevalue.global.config;

import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.global.auth.JwtProvider;
import com.sk.namevalue.global.filter.JwtAuthorizationFilter;
import com.sk.namevalue.infra.oauth2.CustomFailureHandler;
import com.sk.namevalue.infra.oauth2.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * title        : Spring Security Config
 * author       : sim
 * date         : 2023-08-31
 * description  : 스프링 시큐리티 설정
 */

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증으로 인한 Session 미생성.
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(new CustomSuccessHandler(userRepository, jwtProvider))
                .failureHandler(new CustomFailureHandler())
                .and()
                .addFilterAfter(new JwtAuthorizationFilter(jwtProvider), OAuth2LoginAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/login", "/error/*", "/img/**", "/favicon.ico");
    }
}
