package com.xuan.hazy.model.domain;

import lombok.Data;

/**
 * @author xuan
 * @since 2020/3/29
 */
@Data
public class DataLineField {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段值
     */
    private String value;

    /**
     * 字段值类型
     */
    private DataLineFiledType type;

}
