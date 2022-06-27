package com.saucesubfresh.starter.cache.manager;

import com.saucesubfresh.starter.cache.core.ClusterCache;
import com.saucesubfresh.starter.cache.core.RedisCaffeineCache;
import com.saucesubfresh.starter.cache.factory.CacheConfig;
import com.saucesubfresh.starter.cache.factory.ConfigFactory;
import com.saucesubfresh.starter.cache.message.CacheMessageListener;
import com.saucesubfresh.starter.cache.properties.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: 李俊平
 * @Date: 2022-06-18 23:13
 */
public class RedisCaffeineCacheManager extends AbstractCacheManager {

    private final CacheProperties properties;
    private final CacheMessageListener producer;
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCaffeineCacheManager(CacheProperties properties,
                                     ConfigFactory configFactory,
                                     CacheMessageListener producer,
                                     RedisTemplate<String, Object> redisTemplate) {
        super(configFactory);
        this.properties = properties;
        this.producer = producer;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected ClusterCache createCache(String cacheName, CacheConfig cacheConfig) {
        String namespace = properties.getNamespace();
        return new RedisCaffeineCache(cacheName, namespace, cacheConfig, producer, redisTemplate);
    }
}
