package com.sk.namevalue.domain.user.dao.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.namevalue.domain.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import static com.sk.namevalue.domain.user.domain.QUserEntity.userEntity;

/**
 * title        : UserRepositoryImpl
 * author       : sim
 * date         : 2023-09-06
 * description  : User에 대한 QueryDsl 구현체 클래스
 */

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 유저 조회 - 이메일
     * @param email - 이메일
     * @return UserEntity
     */
    @Override
    public UserEntity findByEmail(String email) {

        return queryFactory
                .selectFrom(userEntity)
                .where(
                        eqEmail(email))
                .fetchOne();
    }

    public BooleanExpression eqEmail(String email){
        return email == null ? null : userEntity.email.eq(email);
    }
}
