package com.demo.config.www.service.cache;

import com.demo.config.www.service.common.LifeCycle;

/**
 * @author Wang Junbo
 * @Description 缓存服务接口
 * @create 2020-08-28 11:16
 */
public interface CacheService extends LifeCycle {
    /**
     * 缓存刷新
     */
    void refresh();

    /**
     * 是否正在刷新
     * @return
     */
    boolean isRefresh();

    /**
     * 清空缓存
     */
    void clearAll();
}
