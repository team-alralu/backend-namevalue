package com.sk.namevalue.global.config;

import com.sk.namevalue.global.auth.JwtProvider;
import com.sk.namevalue.global.filter.JwtAuthorizationFilter;
import com.sk.namevalue.infra.oauth2.CustomFailureHandler;
import com.sk.namevalue.infra.oauth2.CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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

    private final JwtProvider jwtProvider;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomFailureHandler customFailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                    .antMatchers("/api/**").hasRole("USER")
                    .anyRequest().authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 인증으로 인한 Session 미생성.
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(customSuccessHandler)
                .failureHandler(customFailureHandler)
                .and()
                .addFilterAfter(new JwtAuthorizationFilter(jwtProvider), OAuth2LoginAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(PathRequest.toH2Console())
                .antMatchers("/view/login", "/error/**");
    }
}
