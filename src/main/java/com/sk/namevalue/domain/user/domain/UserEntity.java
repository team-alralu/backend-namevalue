package com.sk.namevalue.domain.user.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.model.enums.OAuthType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * title        : 사용자 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 사용자 엔티티
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name ="oauth_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OAuthType oauthType;

    private UserEntity(String email, String name, OAuthType oauthType){
        this.email = email;
        this.name = name;
        this.oauthType = oauthType;
    }

    public static UserEntity from(Map<String, Object> oAuth2Attributes){
        String email = (String) oAuth2Attributes.get("email");
        String name = (String) oAuth2Attributes.get("name");
        OAuthType oAuthType = OAuthType.valueOf(oAuth2Attributes.get("oauthType").toString());
        return new UserEntity(email, name, oAuthType);
    }
}
