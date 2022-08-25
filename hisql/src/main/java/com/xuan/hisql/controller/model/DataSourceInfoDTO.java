package com.xuan.hisql.controller.model;

import lombok.Data;

/**
 * @author xuan
 * @since 2022/8/23
 */
@Data
public class DataSourceInfoDTO {

    private Long id;

    private String name;

    private String description;

    private String dataSourceType;

    private String url;

    private String username;

    private String password;

    private String modifyTime;
}
