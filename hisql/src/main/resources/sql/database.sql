CREATE TABLE `hisql_datasource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `creator` varchar(1024) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(1024) DEFAULT NULL COMMENT '修改人',
  `name` varchar(128) NOT NULL COMMENT '数据源名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `connect_status` varchar(128) NOT NULL COMMENT '连通状态，例如：ON、OFF',
  `datasource_type` varchar(128) NOT NULL COMMENT '数据源类型，例如：MYSQL',
  `version` bigint(20) unsigned NOT NULL COMMENT '数据版本',
  `feature` text COMMENT '扩展属性'
PRIMARY KEY (`id`),
KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='数据源'
;
