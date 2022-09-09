package com.xuan.hitools.yuntai;

import java.util.List;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xuan
 * @since 2022/8/31
 */
@Component
public class YunTaiDBHelper implements InitializingBean {

    private JdbcTemplate jdbcTemplate;

    private final static String SQL_CREATE_TABLE = String.format(""
        + "CREATE TABLE IF NOT EXISTS %s("
        + "id               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',"
        + "name             varchar(128) NOT NULL COMMENT '文件名',"
        + "file_type        varchar(128) NOT NULL COMMENT '文件类型：例如file，dir',"
        + "content_type     varchar(128) NOT NULL COMMENT '文件内容类型：例如db，url',"
        + "content          text NULL COMMENT '文件内容',"
        + "parent_id        bigint(20) NOT NULL COMMENT '父级id',"
        + "tenant           varchar(128) NOT NULL COMMENT '租户',"
        + "create_time      datetime NOT NULL COMMENT '创建时间',"
        + "creator          varchar(256) NOT NULL COMMENT '创建人',"
        + "modify_time      datetime NOT NULL COMMENT '修改时间',"
        + "modifier         varchar(256) NOT NULL COMMENT '修改人',"
        + "PRIMARY KEY (`id`),"
        + "KEY idx_name (`name`)"
        + ")", "yuntai_file");

    @Override
    public void afterPropertiesSet() throws Exception {
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl("jdbc:mysql://127.0.0.1:3306/bpmweb?useUnicode=true&characterEncoding=utf8");
        dds.setUsername("bpmweb");
        dds.setPassword("123456");
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        jdbcTemplate = new JdbcTemplate(dds);
        jdbcTemplate.update(SQL_CREATE_TABLE);
    }

    public List<YunTaiFile> getFiles(Long parentId) {
        parentId = null == parentId ? 0L : parentId;
        List<YunTaiFile> fileList = jdbcTemplate.queryForList("SELECT * FROM yuntai_file WHERE parentId=?",
            YunTaiFile.class, parentId);

        if (null != fileList) {
            fileList.forEach(YunTaiFile::formatTime);
        }

        return fileList;
    }

}
