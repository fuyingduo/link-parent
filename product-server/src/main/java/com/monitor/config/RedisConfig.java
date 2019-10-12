package com.monitor.config;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by fuyd on 2019-07-15
 */
@EnableCaching
@Configuration
public class RedisConfig {

    @Value("${cache.default.expire-time:1800}")
    private long defaultExpireTime;

    private static final String CACHE_ROOT = "root_cache";
    private static final String CACHE_DATE = "date_cache";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory lettuceConnectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration
                // 默认过期时间
                .entryTtl(Duration.ofSeconds(defaultExpireTime))
                // 是否储存NULL值
                .disableCachingNullValues()
                // key为String序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        HashSet<String> cacheNames = Sets.newHashSet(CACHE_ROOT, CACHE_DATE);
        LOGGER.info("[REDIS] cacheNames set:{}", cacheNames);
        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                // 默认缓存
                .cacheDefaults(configuration)
                // 初始化缓存名称
                .initialCacheNames(cacheNames)
                // 缓存分类配置
                .withInitialCacheConfigurations(cacheConfigurations(configuration))
                .build();
        LOGGER.info("[REDIS] chacheNames:{}", cacheManager.getCacheNames());
        return cacheManager;
    }

    private Map<String, RedisCacheConfiguration> cacheConfigurations(RedisCacheConfiguration configuration) {
        ConcurrentHashMap<String, RedisCacheConfiguration> cacheConfigurations = new ConcurrentHashMap<>();
        cacheConfigurations.put(CACHE_ROOT, configuration);
        cacheConfigurations.put(CACHE_DATE, configuration.withConversionService(new WebConversionService(DEFAULT_DATE_FORMAT)));
        return cacheConfigurations;
    }
}
