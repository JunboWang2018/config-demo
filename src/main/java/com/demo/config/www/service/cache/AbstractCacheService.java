package com.demo.config.www.service.cache;

/**
 * @Description 缓存服务抽象类
 * @author  Wang Junbo
 * @create 2020-06-03 14:10
 */
public abstract class AbstractCacheService implements CacheService {

    /**
     * 刷新状态
     */
    protected volatile boolean isRefresh = false;


    /**
     * 是否正在刷新
     * @return
     */
    @Override
    public boolean isRefresh() {
        return this.isRefresh;
    }

}
