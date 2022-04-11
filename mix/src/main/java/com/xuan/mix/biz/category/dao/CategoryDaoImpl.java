package com.xuan.mix.biz.category.dao;

import javax.sql.DataSource;

import com.xuan.mix.biz.category.model.CategoryAdd;

/**
 * @author xuan
 * @since 2022/3/30
 */
public class CategoryDaoImpl implements CategoryDao {
    private final static String TABLE_NAME = "biz_category";

    private final static String SQL_CREATE_TABLE = String.format(""
            + "CREATE TABLE IF NOT EXISTS %s("
            + "id BIGINT NOT NULL COMMENT '类目ID',"
            + "name VARCHAR(32) NOT NULL COMMENT '类目名称',"
            + "desc VARCHAR(1024) NOT NULL COMMENT '类目描述',"
            + "create_time DATETIME NOT NULL COMMENT '创建时间',"
            + "modify_time DATETIME NOT NULL COMMENT '修改时间',"
            + "creator VARCHAR(512) NULL COMMENT '创建人',"
            + "modifier VARCHAR(512) NULL COMMENT '修改人',"
            + "version BIGINT NOT NULL COMMENT '数据版本',"
            + "status INT NOT NULL COMMENT '状态：1正常，-1删除',"
            + "parent_id BIGINT NOT NULL DEFAULT 0 COMMENT '父类目id，根类目默认为0',"
            + "feature TEXT NULL COMMENT '扩展字段kv格式',"
            + "PRIMARY KEY (`id`),"
            + "KEY `idx_name` (`name`),"
            + "KEY `idx_parent_id` (`parent_id`)"
            + ")ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COMMENT='类目表'",
        TABLE_NAME);

    private DataSource dataSource;

    public CategoryDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Long addCategory(CategoryAdd categoryAdd) {
        return null;
    }

}
