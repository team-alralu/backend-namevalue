package com.sk.namevalue.domain.user.dao;

import com.sk.namevalue.domain.user.dao.querydsl.UserRepositoryCustom;
import com.sk.namevalue.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        : User Repository
 * author       : sim
 * date         : 2023-09-06
 * description  : User Repository
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
}
