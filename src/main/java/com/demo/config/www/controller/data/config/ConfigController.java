/**
 * 
 * Project Name: scooper-video
 * File Name: ConfigController.java
 * Package Name: cn.com.showclear.www.controller
 * Description: 
 * Copyright: Copyright (c) 2017
 * Company: 杭州叙简科技股份有限公司
 * @version 1.4.0
 * @author ZHENGKAI
 * @date 2017年4月17日下午4:07:08
 */
package com.demo.config.www.controller.data.config;

import cn.com.scooper.common.resp.APIRespJson;
import cn.com.scooper.common.resp.ResultCode;
import com.demo.config.www.controller.data.BaseDataController;
import com.demo.config.www.pojo.config.qo.ConfigQo;
import com.demo.config.www.pojo.config.vo.ConfigVo;
import com.demo.config.www.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配置相关操作
 * @author wjb
 */
@RestController
@RequestMapping("/data/sysConfig")
public class ConfigController extends BaseDataController {

	@Autowired
	private ConfigService configService;


	/**
	 * 查询配置类型列表
	 * @param query
	 * @return
	 */
	@PostMapping(value = "/listSysConfig")
	public APIRespJson queryConfigList(ConfigQo query) {
		return this.responseList(configService.queryConfigList(query));
	}

	/**
	 * 批量修改配置项
	 * @param configList
	 * @return
	 */
	@PostMapping(value = "/updateSysConfigs")
	public APIRespJson updateMultiConfig(@RequestBody List<ConfigVo> configList) {
		int result = this.configService.updateMultiConfig(configList);
		if (result > 0) {
			return this.response(ResultCode.SUCC, "修改成功！");
		} else {
			return this.response(ResultCode.FAIL, "修改失败！");
		}
	}

	/**
	 * 所有配置项恢复默认
	 * @return
	 */
	@GetMapping(value = "/restoreDefault")
	public APIRespJson restoreAllConfig() {
		this.configService.restoreAllConfig();
		return this.response(ResultCode.SUCC, "恢复默认成功！");
	}

	/**
	 * 刷新缓存
	 * @return
	 */
	@GetMapping(value = "/refreshConfigCache")
	public APIRespJson refreshConfigCache() {
		this.configService.refreshConfigCache();
		return this.response(ResultCode.SUCC, "刷新缓存成功！");
	}

}
