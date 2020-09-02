package com.demo.config.www.service.config;

import com.demo.config.www.pojo.config.vo.ConfigTypeVo;

import java.util.List;

/**
 * @author Wang Junbo
 * @Description
 * @create 2020-08-27 20:50
 */
public interface ConfigTypeService {
    /**
     * 查询配置类型列表
     * @return
     */
    List<ConfigTypeVo> queryConfigTypeList();
}
