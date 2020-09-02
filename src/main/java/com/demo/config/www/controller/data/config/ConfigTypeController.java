package com.demo.config.www.controller.data.config;

import cn.com.scooper.common.resp.APIRespJson;
import com.demo.config.www.controller.data.BaseDataController;
import com.demo.config.www.service.config.ConfigTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Junbo
 * @Description 配置类型
 * @create 2020-08-26 11:56
 */
@RestController
@RequestMapping("/data/sysConfigType")
public class ConfigTypeController extends BaseDataController {

    @Autowired
    private ConfigTypeService configTypeService;


    /**
     * 查询配置类型列表
     * @return
     */
    @GetMapping(value = "/listSysConfigType")
    public APIRespJson queryConfigTypeList() {
        return this.responseList(configTypeService.queryConfigTypeList());
    }
}
