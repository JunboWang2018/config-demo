package com.demo.config.www.service.config;

import com.demo.config.www.pojo.config.qo.ConfigQo;
import com.demo.config.www.pojo.config.vo.ConfigVo;

import java.util.List;

/**
 * @author Wang Junbo
 * @Description
 * @create 2020-08-27 20:49
 */
public interface ConfigService {
    /**
     * 查询配置项列表
     * @param query 查询条件
     * @return
     */
    List<ConfigVo> queryConfigList(ConfigQo query);

    /**
     * 批量修改配置
     * @param configList
     * @return
     */
    int updateMultiConfig(List<ConfigVo> configList);

    /**
     * 全部恢复默认
     * @return
     */
    int restoreAllConfig();

    /**
     * 刷新缓存
     */
    void refreshConfigCache();
}
