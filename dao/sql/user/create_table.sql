CREATE TABLE `bw_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `biz_code` varchar(50) NOT DEFAULT 'DEFAULT',
  `username` varchar(500) NOT NULL COMMENT '用户名',
  `name` varchar(500) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(500) DEFAULT NULL COMMENT '邮箱',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `password` varchar(500) DEFAULT NULL COMMENT '加密的密码',
  `type` tinyint(4) DEFAULT NULL COMMENT '用户类型：1-普通',
  `status` tinyint(4) DEFAULT NULL COMMENT '用户状态：0-正常；-1-删除',
  `feature` text DEFAULT NULL COMMENT '属性JSON串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_username_biz_code` (`username`,`biz_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;