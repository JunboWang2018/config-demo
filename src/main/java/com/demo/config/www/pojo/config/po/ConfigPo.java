package com.demo.config.www.pojo.config.po;

import java.io.Serializable;
import java.util.Date;

/**
 * (TSysConfig)实体类
 *
 * @author makejava
 * @since 2020-08-27 16:32:31
 */
public class ConfigPo implements Serializable {
    private static final long serialVersionUID = 788562185329342741L;
    /**
    * 自增主键
    */
    private Object id;
    /**
    * 配置项key
    */
    private String cfgKey;
    /**
    * 配置项显示名称
    */
    private String cfgLabel;
    /**
    * 配置项类型，关联表T_SYS_CONFIG_TYPE.id
    */
    private Object cfgTypeId;
    /**
    * 配置项值
    */
    private String cfgValue;
    /**
    * 置项值类型，基础类型：string,integer,float,boolean,array,enum:{enumTypeId}；在模块中可定义私有类型
    */
    private String valueType;
    /**
    * 配置项默认值
    */
    private String defaultValue;
    /**
    * 配置项辅助参数
    */
    private String cfgOpts;
    /**
    * 配置项显示排序
    */
    private Integer sortIndex;
    /**
    * 系统配置，1：是（更改后需重启）；0：否
    */
    private Object isSystem;
    /**
    * 是否web前端使用
    */
    private Object isFrontUse;
    /**
    * 配置项说明
    */
    private String description;
    /**
    * 是否可用，1：可用；0：不可用
    */
    private Object isActive;
    /**
    * 本条记录最后更新时间
    */
    private Date updateTime;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey;
    }

    public String getCfgLabel() {
        return cfgLabel;
    }

    public void setCfgLabel(String cfgLabel) {
        this.cfgLabel = cfgLabel;
    }

    public Object getCfgTypeId() {
        return cfgTypeId;
    }

    public void setCfgTypeId(Object cfgTypeId) {
        this.cfgTypeId = cfgTypeId;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCfgOpts() {
        return cfgOpts;
    }

    public void setCfgOpts(String cfgOpts) {
        this.cfgOpts = cfgOpts;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Object getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Object isSystem) {
        this.isSystem = isSystem;
    }

    public Object getIsFrontUse() {
        return isFrontUse;
    }

    public void setIsFrontUse(Object isFrontUse) {
        this.isFrontUse = isFrontUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        this.isActive = isActive;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}