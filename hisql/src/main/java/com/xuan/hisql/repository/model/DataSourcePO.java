package com.xuan.hisql.repository.model;

import java.util.Date;

import com.xuan.dao.common.DataModel;
import com.xuan.dao.common.NameValuePair;
import lombok.Data;

/**
 * @author xuan
 * @since 2022/8/25
 */
@Data
public class DataSourcePO implements DataModel {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String creator;

    private String modifier;

    private String name;

    private String description;

    private String connectStatus;

    private String datasourceType;

    private Long version;

    private String feature;

    @Override
    public String tableName() {
        return "hisql_datasource";
    }

    @Override
    public NameValuePair primaryKey() {
        return NameValuePair.of("id", id);
    }

    @Override
    public NameValuePair version() {
        return NameValuePair.of("version", version);
    }

}
