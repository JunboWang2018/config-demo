package com.demo.config.www.dao.config;

import com.demo.config.www.pojo.config.vo.ConfigTypeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TSysConfigType)表数据库访问层
 *
 * @author Wang Junbo
 * @since 2020-08-27 16:33:00
 */
@Repository
public interface ConfigTypeDao {

    /**
     * 查询配置类型列表
     * @return
     */
    List<ConfigTypeVo> queryConfigTypeList();

}