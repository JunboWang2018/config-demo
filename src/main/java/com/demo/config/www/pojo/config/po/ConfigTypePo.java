package com.demo.config.www.pojo.config.po;

import java.io.Serializable;
import java.util.Date;

/**
 * (TSysConfigType)实体类
 *
 * @author makejava
 * @since 2020-08-27 16:33:00
 */
public class ConfigTypePo implements Serializable {
    private static final long serialVersionUID = 682401192229275460L;
    /**
    * 自增id
    */
    private Integer id;
    /**
    * 类型key
    */
    private String typeKey;
    /**
    * 类型名称
    */
    private String typeName;
    /**
    * 排序字段
    */
    private Integer sortIndex;
    /**
    * 拓展属性
    */
    private String typeExt;
    /**
    * 是否可用，1：可用；0：不可用
    */
    private Object isActive;
    /**
    * 本条记录最后更新时间
    */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getTypeExt() {
        return typeExt;
    }

    public void setTypeExt(String typeExt) {
        this.typeExt = typeExt;
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