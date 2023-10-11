package com.sk.namevalue.infra.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.namevalue.domain.model.enums.Token;
import com.sk.namevalue.domain.token.dto.TokenDto;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.auth.JwtProvider;
import com.sk.namevalue.global.util.HttpUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * title        : CustomSuccessHandler
 * author       : sim
 * date         : 2023-09-06
 * description  : 인증 성공 후처리 클래스로 CustomOAuth2UserService 처리 후 호출된다.
 */

@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        Map<String,Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            log.info(email+" 계정에 대한 신규 유저를 생성합니다.");
            userEntity = UserEntity.from(attributes);
            userRepository.save(userEntity);
        }

        Map<String, Object> claims = jwtProvider.generateClaims(userEntity);

        String accessToken = jwtProvider.generateAccessToken(claims);
        String refreshToken = jwtProvider.generateRefreshToken(claims);

        log.info(accessToken);
        log.info(refreshToken);

        Cookie cookie = HttpUtil.generateSecureCookie(Token.REFRESH_TOKEN.getKey(), refreshToken, Token.REFRESH_TOKEN.getMaxAge());
        response.addCookie(cookie);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String responseBody = objectMapper.writeValueAsString(TokenDto.from(accessToken));

        try(PrintWriter printWriter = response.getWriter()){
            printWriter.write(responseBody);
        }

        log.info("인증이 완료되었습니다.");
    }
}
