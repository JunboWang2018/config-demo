package com.demo.config.www.service.common;

/**
 * @Description 生命周期
 * @author Wang Junbo
 * @create 2020-06-03 14:01
 */
public interface LifeCycle {
    /**
     * 启动
     */
    void startup();

    /**
     * 关闭
     */
    void shutdown();
}
