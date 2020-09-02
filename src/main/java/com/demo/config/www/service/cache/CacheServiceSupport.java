package com.demo.config.www.service.cache;

import com.demo.config.www.common.exception.cache.CacheExistingException;
import com.demo.config.www.service.common.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 缓存服务支持
 * @author Wang Junbo
 * @create 2020-06-15 16:08
 */
@Service
public final class CacheServiceSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceSupport.class);

    /**
     * 缓存服务对象生命周期管理
     */
    private Map<String, LifeCycle> cacheLifeCycleMap = new ConcurrentHashMap<>();

    /**
     * 启动所有缓存
     */
    public void startAll() {
        for (Map.Entry<String, LifeCycle> cache : cacheLifeCycleMap.entrySet()) {
            cache.getValue().startup();
        }
    }

    /**
     * 关闭所有缓存
     */
    private void shutdownAll() {
        for (Map.Entry<String, LifeCycle> cache : cacheLifeCycleMap.entrySet()) {
            cache.getValue().shutdown();
        }
    }

    /**
     * 添加缓存服务对象，默认不覆盖
     * @param key
     * @param value
     */
    public void putCacheLifeCycle(String key, LifeCycle value) throws CacheExistingException {
        this.putCacheLifeCycle(key, value, false);
    }

    /**
     * 添加缓存服务对象
     * 当isOverride=false，但是缓存对象已存在时，会抛出异常
     * @param key
     * @param value
     * @param isOverride  是否覆盖
     */
    public void putCacheLifeCycle(String key, LifeCycle value, boolean isOverride) throws CacheExistingException {
        LifeCycle existingValue = this.cacheLifeCycleMap.get(key);
        if (existingValue != null && !isOverride) {
            throw new CacheExistingException("Can not override cache value. key is " + key);
        } else if (existingValue != null) {
            LOGGER.info("Override cacheLifeCycleMap item which key is " + key);
            this.cacheLifeCycleMap.put(key, value);
        } else {
            LOGGER.info("Add cacheLifeCycleMap item which key is " + key);
            this.cacheLifeCycleMap.put(key, value);
        }
    }

    /**
     * 根据名称获取缓存对象
     * @param key
     * @return
     */
    public LifeCycle getCacheLifeCycle(String key) {
        return this.cacheLifeCycleMap.get(key);
    }

    /**
     * 获取所有缓存对象
     * @return
     */
    public Set<LifeCycle> getAllCacheLifeCycle() {
        Set<LifeCycle> caches = new HashSet<>();
        caches.addAll(this.cacheLifeCycleMap.values());
        return caches;
    }

    /**
     * 移除缓存服务对象
     * @param key
     */
    public void removeCacheLifeCycle(String key) {
        LifeCycle removeValue = this.cacheLifeCycleMap.remove(key);
        if (removeValue != null) {
            LOGGER.info("Remove cacheLifeCycleMap item which key is " + key);
        }
    }

    /**
     * 移除所有
     */
    public void clearAll () {
        this.cacheLifeCycleMap.clear();
    }
}
