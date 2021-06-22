package com.bench.lock.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @className RedisLockConfiguration
 * @autor cold
 * @DATE 2021/6/22 17:35
 **/
@Configuration
public class RedisLockConfiguration {
    /**
     * 注册的KEY
     */
    private final static String LOCK_REGISTRY_KEY = "lock";
    /**
     * 过期时间，默认为10秒
     */
    private final static long EXPIRE_AFTER_MILLI = 10*1000;
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {

        return new RedisLockRegistry(redisConnectionFactory, LOCK_REGISTRY_KEY,EXPIRE_AFTER_MILLI);
    }
}
