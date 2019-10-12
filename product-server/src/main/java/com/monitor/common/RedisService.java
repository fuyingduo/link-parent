package com.monitor.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * created by fuyd on 2019-07-02
 */
@Component
public class RedisService<V> {

    @Autowired
    private RedisTemplate<String, V> redisTemplate;

    /**
     * 默认增量 1L
     */
    private static final long DEFAULT_INCREMENT = 1L;

    /**
     * 默认超时时间 24 hours
     */
    private static final long DEFAULT_TIMEOUT = 24;
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    public Boolean set(String key, V value) {
        try {
            redisTemplate.opsForValue().set(key, value, DEFAULT_TIMEOUT, TimeUnit.HOURS);
        } catch (Exception e) {
            LOGGER.error("[Redis保存缓存] 异常 e:{}", e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean set(String key, V value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            LOGGER.error("[Redis保存缓存] 异常 e:{}", e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean delete(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            LOGGER.error("[Redis删除缓存] 异常 e:{}", e.getMessage());
        }
        return false;
    }

    public <T> Optional<T> get(String key, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object obj = redisTemplate.opsForValue().get(key);
            return Optional.ofNullable(mapper.convertValue(obj, cls));
        } catch (Exception e) {
            LOGGER.error("[Redis获取缓存] 异常 e:{}", e.getMessage());
        }
        return Optional.empty();
    }

    public Long increment(String key) {
        try {
            return redisTemplate.opsForValue().increment(key, DEFAULT_INCREMENT);
        } catch (Exception e) {
            LOGGER.error("[Redis] 异常 e:{}", e.getMessage());
        }
        return null;
    }

    public Long increment(String key, long delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            LOGGER.error("[Redis] 异常 e:{}", e.getMessage());
        }
        return null;
    }
}
