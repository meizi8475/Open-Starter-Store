package com.saucesubfresh.starter.cache.manager;

import com.saucesubfresh.starter.cache.core.ClusterCache;
import com.saucesubfresh.starter.cache.properties.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * 管理 cache
 * @author: 李俊平
 * @Date: 2022-05-29 13:36
 */
public interface CacheManager {

    /**
     * 获取 ClusterCache
     * @param cacheName 缓存隔离
     * @return ClusterCache
     */
    ClusterCache getCache(String cacheName);

    /**
     * 获取 ClusterCache 列表
     * @return
     */
    Map<String, ClusterCache> getCacheList();

    /**
     * 获取 ClusterCache 列表
     * @return
     */
    Map<String, CacheConfig> getCacheConfigList();
}
