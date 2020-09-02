package com.demo.config.www.service.config.impl;

import com.demo.config.www.dao.config.ConfigTypeDao;
import com.demo.config.www.pojo.config.vo.ConfigTypeVo;
import com.demo.config.www.service.config.ConfigTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wang Junbo
 * @Description 配置类型服务
 * @create 2020-08-26 10:58
 */
@Service
public class ConfigTypeServiceImpl implements ConfigTypeService {


    @Autowired
    private ConfigTypeDao configTypeDao;

    /**
     * 查询配置类型
     * @return
     */
    @Override
    public List<ConfigTypeVo> queryConfigTypeList() {
        List<ConfigTypeVo> configTypeList = configTypeDao.queryConfigTypeList();
        return configTypeList;
    }
}
