package com.demo.config.www.service.cache;

/**
 * @Description 缓存服务抽象类
 * @author  Wang Junbo
 * @create 2020-06-03 14:10
 */
public abstract class AbstractCacheService implements CacheService {

    /**
     * 存活状态
     */
    protected volatile boolean isAlive = false;

    /**
     * 刷新状态
     */
    protected volatile boolean isRefresh = false;

    /**
     * 是否存活
     * @return
     */
    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * 是否正在刷新
     * @return
     */
    @Override
    public boolean isRefresh() {
        return this.isRefresh;
    }

    /**
     * 是否可用  (存活并且不在刷新状态)
     * @return
     */
    public boolean isUsable() {
        return this.isAlive && !this.isRefresh;
    }
}
