package com.sk.namevalue.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * title        : RedisService
 * author       : sim
 * date         : 2023-09-20
 * description  : Redis 서비스 클래스
 */

@Configuration
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<Long,String> tokenRedisTemplate;

    /**
     * 저장하기
     * @param key - key
     * @param value - value
     * @param timeout - timeout
     * @param timeUnit - timeUnit
     */
    public void save(Long key, String value, long timeout, TimeUnit timeUnit){
        tokenRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }
}
