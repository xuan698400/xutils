package com.xuan.hisql.service.model;

import java.util.Date;

import lombok.Data;

/**
 * @author xuan
 * @since 2022/8/23
 */
@Data
public class DataSource {

    private Long id;

    private String name;

    private String description;

    private DataSourceType dataSourceType;

    private DataSourceFeature dataSourceFeature;

    private Date modifyTime;

    private Date createTime;

    private ConnectStatus connectStatus;

}
