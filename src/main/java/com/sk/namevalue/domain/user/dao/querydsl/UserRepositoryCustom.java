package com.sk.namevalue.domain.user.dao.querydsl;

import com.sk.namevalue.domain.user.domain.UserEntity;

/**
 * title        :
 * author       : sim
 * date         : 2023-09-06
 * description  :
 */
public interface UserRepositoryCustom {

    UserEntity findByEmail(String email);
}
