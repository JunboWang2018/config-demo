package com.demo.config.www.pojo.config.qo;

import java.io.Serializable;

/**
 * (TSysConfig)实体类
 *
 * @author makejava
 * @since 2020-08-27 16:32:31
 */
public class ConfigQo implements Serializable {
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
    * 配置项类型，关联表T_SYS_CONFIG_TYPE.id
    */
    private Object typeId;


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

    public Object getTypeId() {
        return typeId;
    }

    public void setTypeId(Object typeId) {
        this.typeId = typeId;
    }
}