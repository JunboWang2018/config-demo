package com.demo.config.www.service.cache.config;

import java.util.List;
import java.util.Map;

/**
 * @author Wang Junbo
 * @Description 配置缓存服务
 * @create 2020-08-28 11:21
 */
public interface ConfigCacheService {

    /**
     * 获取配置值
     * @param key 配置项key
     * @param defaultValue 默认值
     * @param isReplaceRely 是否替换依赖
     * @return
     */
    String get(String key, String defaultValue, boolean isReplaceRely);

    /**
     * 获取配置值，不替换依赖
     * @param key 配置项key
     * @param defaultValue 默认值
     * @return
     */
    String get(String key, String defaultValue);

    /**
     * 获取配置值，替换依赖，默认值为空串
     * @param key 配置项key
     * @param isReplaceRely 是否替换依赖
     * @return
     */
    String get(String key, boolean isReplaceRely);

    /**
     * 获取配置值，不替换依赖
     * @param key 配置项key
     * @return
     */
    String get(String key);

    /**
     * 根据前缀获取键
     * @param prefix
     * @return
     */
    List<String> getKeyByPrefix(String prefix);

    /**
     * 根据前缀获取配置，为空返回所有
     * @param prefix
     * @return
     */
    Map<String, String> getConfigByPrefix(String prefix);

    /**
     * 根据后缀获取键
     * @param suffix
     * @return
     */
    List<String> getKeyBySuffix(String suffix);

    /**
     * 根据前缀和后缀获取
     * @param prefix
     * @param suffix
     * @return
     */
    List<String> getKeyByPrefixAndSuffix(String prefix, String suffix);


}
