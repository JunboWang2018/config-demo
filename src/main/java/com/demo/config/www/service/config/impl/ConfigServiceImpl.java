package com.demo.config.www.service.config.impl;

import com.demo.config.www.dao.config.ConfigDao;
import com.demo.config.www.pojo.config.qo.ConfigQo;
import com.demo.config.www.pojo.config.vo.ConfigVo;
import com.demo.config.www.service.cache.CacheService;
import com.demo.config.www.service.cache.config.ConfigCacheService;
import com.demo.config.www.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Wang Junbo
 * @Description 配置项服务
 * @create 2020-08-26 10:58
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private ConfigCacheService configCacheService;

    /**
     * 查询配置项列表
     * @param query 查询条件
     * @return
     */
    @Override
    public List<ConfigVo> queryConfigList(ConfigQo query) {
        query = query == null ? new ConfigQo() : query;
        return configDao.queryConfigList(query);
    }

    /**
     * 批量更新配置项
     * @param configList
     * @return
     */
    @Override
    public int updateMultiConfig(List<ConfigVo> configList) {
        Assert.notEmpty(configList, "配置项为空！");
        // key不能为空
        for (int i = 0; i < configList.size(); i++) {
            Assert.notNull(configList.get(i).getCfgKey(), "配置项key不能为空");
        }
        int result = configDao.updateMultiConfig(configList);
        if (result > 0) {
            // 刷新缓存
            this.refreshAllConfigs();
        }
        return result;
    }

    /**
     * 所有配置项恢复默认
     * @return
     */
    @Override
    public int restoreAllConfig() {
        int result = configDao.restoreAllConfig();
        if (result > 0) {
            // 刷新缓存
            this.refreshAllConfigs();
        }
        return result;
    }

    /**
     * 刷新缓存
     */
    @Override
    public void refreshConfigCache() {
        // 刷新缓存
        this.refreshAllConfigs();
    }

    /**
     * 刷新缓存
     */
    private void refreshAllConfigs() {
        if (configCacheService instanceof CacheService) {
            ((CacheService) configCacheService).refresh();
        }
    }
}
