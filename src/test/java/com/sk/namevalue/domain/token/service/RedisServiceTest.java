package com.sk.namevalue.domain.token.service;

import com.sk.namevalue.config.TestFixture;
import com.sk.namevalue.domain.model.enums.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * title        : RedisServiceTest
 * author       : sim
 * date         : 2023-09-20
 * description  : RedisServiceTest
 */
class RedisServiceTest implements TestFixture {

    private final RedisTemplate<Long, String> redisTemplate = mock(RedisTemplate.class);
    private final ValueOperations valueOperations = mock(ValueOperations.class);
    private final RedisService redisService = new RedisService(redisTemplate);

    @BeforeEach
    void setUp(){
        given(redisTemplate.opsForValue()).willReturn(valueOperations);
    }
    @Test
    void 토큰_저장하기(){
        redisService.save(VALID_USERID, VALID_REFRESH_TOKEN, Token.REFRESH_TOKEN.getMaxAge(), TimeUnit.MINUTES);
        verify(valueOperations).set(VALID_USERID, VALID_REFRESH_TOKEN, Token.REFRESH_TOKEN.getMaxAge(), TimeUnit.MINUTES);
    }
}