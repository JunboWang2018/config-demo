package com.demo.config.www.service.cache.config.impl;

import cn.com.scooper.common.exception.BusinessException;
import cn.com.scooper.common.resp.ResultCode;
import cn.com.scooper.common.util.StringUtils;
import com.demo.config.www.pojo.config.qo.ConfigQo;
import com.demo.config.www.pojo.config.vo.ConfigVo;
import com.demo.config.www.service.cache.AbstractCacheService;
import com.demo.config.www.service.cache.config.ConfigCacheService;
import com.demo.config.www.service.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wang Junbo
 * @Description 配置缓存服务实现类
 * @create 2020-08-28 11:22
 */
@Service
public class ConfigCacheServiceImpl extends AbstractCacheService implements ConfigCacheService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigCacheServiceImpl.class);
    /**
     * 配置集中缓存 保证有序
     */
    private Map<String, String> configCache = new LinkedHashMap<>(128);
    /**
     * 缓存名字
     */
    public static final String CACHE_NAME = "configCacheService";
    /**
     * 依赖解析
     */
    private RelyParser parser = new RelyParser();

    @Autowired
    private ConfigService configService;

    @Resource(name = "configProperties")
    private Properties configProperties;


    /**
     * 刷新缓存
     * 清除 并 加载
     * 唤醒刷新过程中等待的线程  notify需要在isRefresh=false之后
     */
    @Override
    public void refresh() {
        if (isRefresh) {
            return;
        }
        this.isRefresh = true;
        this.clearAll();
        this.loadConfigs();
        this.isRefresh = false;
        synchronized (this) {
            this.notifyAll();
        }
    }

    /**
     * 清除所有缓存
     */
    @Override
    public void clearAll() {
        this.configCache.clear();
    }

    /**
     * 启动
     */
    @Override
    public void startup() {
        this.isAlive = true;
        // 加载配置
        this.loadConfigs();
        LOGGER.info("Config cache service is started");
    }

    /**
     * 关闭
     */
    @Override
    public void shutdown() {
        // 清除配置
        this.clearAll();
        LOGGER.info("Config cache service shutdown");
        this.isAlive = false;
    }

    /**
     * 加载配置
     */
    private void loadConfigs() {
        this.loadDbConfigs();
        this.loadFileConfigs();
    }

    /**
     * 加载配置文件中的配置
     * 通过configProperties加载
     */
    private void loadFileConfigs() {
        for (Map.Entry<Object, Object> config : this.configProperties.entrySet()) {
            this.configCache.put(config.getKey().toString(), config.getValue().toString());
        }
    }

    /**
     * 加载数据库中的配置
     */
    private void loadDbConfigs() {
        List<ConfigVo> configList = this.configService.queryConfigList(new ConfigQo());
        for (int i = 0; i < configList.size(); i++) {
            ConfigVo config = configList.get(i);
            this.configCache.put(config.getCfgKey(), config.getCfgValue());
        }
    }

    /**
     * 获取配置值
     * @param key 配置项key
     * @param defaultValue 默认值
     * @param isReplaceRely 是否替换依赖
     * @return
     */
    @Override
    public String get(String key, String defaultValue, boolean isReplaceRely) {
        this.checkBeforeGet();
        String value = configCache.get(key);
        if (StringUtils.isEmpty(value) && StringUtils.notEmpty(defaultValue)) {
            value = defaultValue;
        } else if (StringUtils.isEmpty(value)) {
            return "";
        }
        if (isReplaceRely && parser.hasRely(value)) {
            return parser.replaceRely(value);
        }
        return value;
    }

    /**
     * 获取配置值，不替换依赖
     * @param key 配置项key
     * @param defaultValue 默认值
     * @return
     */
    @Override
    public String get(String key, String defaultValue) {
        return this.get(key, defaultValue, false);
    }

    /**
     * 获取配置值，替换依赖，默认值为空串
     * @param key 配置项key
     * @param isReplaceRely 是否替换依赖
     * @return
     */
    @Override
    public String get(String key, boolean isReplaceRely) {
        return this.get(key, "", isReplaceRely);
    }

    /**
     * 获取配置值，不替换依赖
     * @param key 配置项key
     * @return
     */
    @Override
    public String get(String key) {
        return this.get(key, "");
    }

    /**
     * 根据前缀获取键
     * @param prefix
     * @return
     */
    @Override
    public List<String> getKeyByPrefix(String prefix) {
        return this.getKeyByPrefixAndSuffix(prefix, "");
    }

    /**
     * 根据前缀获取配置
     * @param prefix
     * @return
     */
    @Override
    public Map<String, String> getConfigByPrefix(String prefix) {
        Map<String, String> configs = new HashMap<>();
        List<String> keyList = this.getKeyByPrefix(prefix);
        for (int i = 0; i < keyList.size(); i++) {
            String value = this.configCache.get(keyList.get(i));
            configs.put(keyList.get(i), value);
        }
        return configs;
    }

    /**
     * 根据后缀获取键
     * @param suffix
     * @return
     */
    @Override
    public List<String> getKeyBySuffix(String suffix) {
        return this.getKeyByPrefixAndSuffix("", suffix);
    }

    /**
     * 根据前缀和后缀获取
     * @param prefix
     * @param suffix
     * @return
     */
    @Override
    public List<String> getKeyByPrefixAndSuffix(String prefix, String suffix) {
        this.checkBeforeGet();
        List<String> keyList = new ArrayList<>();
        boolean isEmpty = StringUtils.isEmpty(prefix);
        for (Map.Entry config : this.configCache.entrySet()) {
            String key = config.getKey().toString();
            if (isEmpty) {
                keyList.add(key);
            } else if (StringUtils.notEmpty(key) && key.startsWith(prefix) && key.endsWith(suffix)) {
                keyList.add(config.getKey().toString());
            }
        }
        return keyList;
    }

    /**
     * 检查缓存是否在刷新中
     * 刷新中需要等待
     * TODO 如果用到的方法较多，考虑用AOP方式拦截
     */
    private void checkBeforeGet() {
        if (this.isRefresh()) {
            synchronized (this) {
                try {
                    while (this.isRefresh()) {
                        LOGGER.info("Cache is refreshing, waiting...");
                        this.wait(5000L);
                        // 超时未刷新完直接返回
                        if(this.isRefresh()) {
                            throw new BusinessException(ResultCode.FAIL, "缓存刷新中，请求超时！");
                        }
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("Error in waiting cache refresh!", e);
                }
            }
        }
    }


    /**
     * 解析依赖
     */
    class RelyParser {
        private final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
        private final Pattern PATTERN3 = Pattern.compile("\\{[^\\}]+\\}");

        /**
         * 替换依赖
         * @param value
         * @return
         */
        public String replaceRely(String value) {
            if (StringUtils.isEmpty(value)) {
                return "";
            }
            Matcher matcherFind = PATTERN.matcher(value);
            if (matcherFind.find()) {
                Matcher matcherReplace = PATTERN3.matcher(value);
                while (matcherReplace.find()) {
                    String key2 = matcherReplace.group().replaceAll("[\\{\\}]", "");
                    // 查找依赖 继续替换
                    value = value.replace("${" + key2 + "}", get(key2, "", true));
                }
            }
            return value;
        }

        /**
         * 是否包含依赖
         * @param value
         * @return
         */
        public boolean hasRely(String value) {
            if (StringUtils.isEmpty(value)) {
                return false;
            }
            Matcher matcherFind = PATTERN.matcher(value);
            if (matcherFind.find()) {
                return true;
            }
            return false;
        }

    }
}
