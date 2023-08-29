package com.sk.namevalue.domain.user.domain;

import com.sk.namevalue.domain.model.enums.OAuthType;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * title        : 사용자 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 사용자 엔티티
 */

@Entity
@Getter
@Table(name = "tbl_user")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name ="oauth_type", nullable = false)
    private OAuthType oauthType;
}
