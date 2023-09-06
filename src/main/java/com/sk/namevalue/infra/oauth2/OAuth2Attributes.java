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

    private final String name;
    private final String email;
    private final OAuthType oauthType;

    public OAuth2Attributes(String name, String email, OAuthType oauthType) {
        this.name = name;
        this.email = email;
        this.oauthType = oauthType;
    }

    public static OAuth2Attributes of(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equals(OAuthType.NAVER.getRegistrationId())){
            return ofNaver(attributes);
        }
        return ofGoogle(attributes);
    }

    /**
     * 카카오 속성을 통한 OAuth2Attributes 정적 팩토리 메서드
     * 카카오의 경우 이름 속성을 가져올 수 없는 관계로 사용하지 않음.
     * @param attributes - 유저 속성
     * @return OAuth2Attributes
     */
    private static OAuth2Attributes ofKakao(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, String> profile = (Map<String, String>) kakaoAccount.get("profile");

        return new OAuth2Attributes(
                profile.get("nickname"),
                (String) kakaoAccount.get("email"),
                OAuthType.KAKAO
        );
    }

    /**
     * 네이버 유저 속성을 통한 OAuth2Attributes 객체 생성 팩토리 메서드
     * @param attributes - 유저 속성
     * @return OAuth2Attributes
     */
    private static OAuth2Attributes ofNaver(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");    // 네이버에서 받은 데이터에서 프로필 정보다 담긴 response 값을 꺼낸다.

        return new OAuth2Attributes(
                (String)response.get("name"),
                (String)response.get("email"),
                OAuthType.NAVER
        );
    }

    /**
     * 구글 유저 속성을 통한 OAuth2Attributes 객체 생성 팩토리 메서드
     * @param attributes - 유저 속성
     * @return OAuth2Attributes
     */
    private static OAuth2Attributes ofGoogle(Map<String, Object> attributes) {
        return new OAuth2Attributes(
                (String)attributes.get("name"),
                (String)attributes.get("email"),
                OAuthType.GOOGLE
        );
    }

}
