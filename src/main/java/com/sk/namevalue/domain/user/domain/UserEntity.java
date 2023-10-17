package com.sk.namevalue.domain.user.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.model.enums.MBTI;
import com.sk.namevalue.domain.model.enums.OAuthType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * title        : 사용자 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 사용자 엔티티
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Comment("이름")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("OAuth2 타입")
    @Column(name ="oauth_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OAuthType oauthType;

    @Comment("네임벨류 등록 횟수")
    @Column(name = "name_value_add_cnt")
    private long nameValueAddCnt;

    @Comment("mbti")
    @Column(name = "mbti")
    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Comment("마지막 이름 등록 일자. [매크로 방지]")
    @Column(name = "last_reg_date")
    private LocalDateTime lastRegDate;

    @Comment("필수정보 등록 여부")
    @Column(name = "required_info_reg_flag")
    private boolean requiredInfoRegFlag = false;

    private UserEntity(String email, String name, OAuthType oauthType){
        this.email = email;
        this.name = name;
        this.oauthType = oauthType;
    }

    public static UserEntity of(String email, String name, OAuthType oauthType){
        return new UserEntity(email, name, oauthType);
    }

    public static UserEntity from(Map<String, Object> oAuth2Attributes){
        String email = (String) oAuth2Attributes.get("email");
        String name = (String) oAuth2Attributes.get("name");
        OAuthType oAuthType = OAuthType.valueOf(oAuth2Attributes.get("oauthType").toString());
        return new UserEntity(email, name, oAuthType);
    }

    /**
     * MBTI 수정
     * @param mbti - mbti
     */
    public void updateMBTI(MBTI mbti){
        this.mbti = mbti;
    }

    /**
     * 마지막 이름 정보 등록 날짜 수정
     */
    public void renewLastNameRegDate(){
        this.lastRegDate = LocalDateTime.now();
    }

    public void updateRequiredInfoRegFlag(boolean flag){
        this.requiredInfoRegFlag = flag;
    }
}
