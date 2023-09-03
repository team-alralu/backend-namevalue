package com.sk.namevalue.infra.oauth2;

import com.sk.namevalue.domain.model.enums.OAuthType;
import lombok.Getter;

import java.util.Map;

/**
 * title        : OAuth2 Attributes
 * author       : sim
 * date         : 2023-09-03
 * description  : OAuth2를 통해 조회한 속성 값들을 관리하는 클래스
 */

@Getter
public class OAuth2Attributes {

    private final String nickname;
    private final String email;

    public OAuth2Attributes(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public static OAuth2Attributes of(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equals(OAuthType.KAKAO.getValue())) {
            return ofKakao(attributes);
        }
        return ofNaver(attributes);
    }

    private static OAuth2Attributes ofKakao(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, String> profile = (Map<String, String>) kakaoAccount.get("profile");

        return new OAuth2Attributes(
                profile.get("nickname"),
                (String) kakaoAccount.get("email")
        );
    }

    private static OAuth2Attributes ofNaver(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");    // 네이버에서 받은 데이터에서 프로필 정보다 담긴 response 값을 꺼낸다.

        return new OAuth2Attributes(
                (String)response.get("nickname"),
                (String)response.get("email")
        );
    }
}
