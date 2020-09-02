/**
 *
 * Project Name: config-demo
 * File Name: InstantiationTracingBeanPostProcessor.java
 * Package Name: com.demo.config.www.config
 * Description: 
 * Copyright: Copyright (c) 2017
 * Company: 杭州叙简科技股份有限公司
 * @version 1.4.0
 * @author ZHENGKAI
 * @date 2017年1月12日下午5:25:53
 */
package com.demo.config.www.config;

import com.demo.config.www.common.exception.cache.CacheExistingException;
import com.demo.config.www.service.cache.CacheServiceSupport;
import com.demo.config.www.service.cache.config.ConfigCacheService;
import com.demo.config.www.service.cache.config.impl.ConfigCacheServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * spring容器初始化完成后业务操作
 * @Reason: TODO ADD REASON(可选). <br/>
 * @date: 2017年1月12日下午5:25:53 <br/>
 *
 * @author ZHENGKAI
 * @version 1.4.0
 */
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationTracingBeanPostProcessor.class);

	@Autowired
	private CacheServiceSupport cacheServiceSupport;

	@Autowired
	private ConfigCacheService configCacheService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("spring容器初始化完成");
		}
		
		if(event.getApplicationContext().getParent() == null) {
			this.initCache();
        }
	}

	/**
	 * 初始化缓存
	 */
	private void initCache() {
		LOGGER.info("Start all cache service");
		try {
			cacheServiceSupport.putCacheLifeCycle(ConfigCacheServiceImpl.CACHE_NAME, (ConfigCacheServiceImpl) configCacheService);
		} catch (CacheExistingException e) {
			LOGGER.error("Cache existing! ", e);
		}
		cacheServiceSupport.startAll();
	}
}
