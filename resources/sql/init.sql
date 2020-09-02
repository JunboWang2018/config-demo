CREATE DATABASE /*!32312 IF NOT EXISTS*/`{db name}` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `{db name}`;

DROP TABLE IF EXISTS `T_SYS_CONFIG`;

CREATE TABLE `T_SYS_CONFIG` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cfg_key` varchar(64) NOT NULL DEFAULT '' COMMENT '配置项key',
  `cfg_label` varchar(32) NOT NULL DEFAULT '' COMMENT '配置项显示名称',
  `cfg_type_id` int(11) unsigned NOT NULL COMMENT '配置项类型，关联表T_SYS_CONFIG_TYPE.id',
  `cfg_value` varchar(128) NOT NULL DEFAULT '' COMMENT '配置项值',
  `value_type` varchar(64) NOT NULL DEFAULT 'string' COMMENT '置项值类型，基础类型：string,integer,float,boolean,array,enum:{enumTypeId}；在模块中可定义私有类型',
  `default_value` varchar(128) NOT NULL COMMENT '配置项默认值',
  `cfg_opts` text COMMENT '配置项辅助参数',
  `sort_index` int(11) NOT NULL DEFAULT '999' COMMENT '配置项显示排序',
  `is_system` tinyint(1) NOT NULL DEFAULT '0' COMMENT '系统配置，1：是（更改后需重启）；0：否',
  `is_front_use` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否web前端使用',
  `description` varchar(512) DEFAULT '' COMMENT '配置项说明',
  `is_active` tinyint(1) NOT NULL COMMENT '是否可用，1：可用；0：不可用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '本条记录最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `T_SYS_CONFIG_TYPE`;

/* 模块配置数据 */

CREATE TABLE `T_SYS_CONFIG_TYPE` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type_key` varchar(32) NOT NULL DEFAULT '' COMMENT '类型key',
  `type_name` varchar(32) NOT NULL DEFAULT '' COMMENT '类型名称',
  `sort_index` int(11) NOT NULL DEFAULT '999' COMMENT '排序字段',
  `type_ext` text COMMENT '拓展属性',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用，1：可用；0：不可用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '本条记录最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* 模块配置数据 */