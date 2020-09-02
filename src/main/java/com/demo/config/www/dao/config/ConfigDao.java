package com.demo.config.www.dao.config;

import com.demo.config.www.pojo.config.qo.ConfigQo;
import com.demo.config.www.pojo.config.vo.ConfigVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TSysConfig)表数据库访问层
 *
 * @author Wang Junbo
 * @since 2020-08-27 16:32:34
 */
@Repository
public interface ConfigDao {

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

}