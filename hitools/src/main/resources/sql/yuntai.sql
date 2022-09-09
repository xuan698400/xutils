CREATE TABLE `tenant` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`gmt_create` datetime NOT NULL COMMENT '创建时间',
`gmt_modified` datetime NOT NULL COMMENT '修改时间',
`biz_key` varchar(32) DEFAULT '' COMMENT '鸿鹄维护的租户标识，与HonghuTenant枚举对应',
`name` varchar(64) DEFAULT '' COMMENT '租户名称',
`platform_id` varchar(64) DEFAULT '' COMMENT '平台ID，需要对应环境配置',
`expand` varchar(256) DEFAULT '' COMMENT '其它信息',
`feature` json DEFAULT NULL COMMENT '业务备用拓展',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='租户信息，用于获取不同环境的租户ID等'
;