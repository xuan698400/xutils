package com.xuan.crudboy.config;

import java.util.List;

/**
 * @author xuan
 * @since 2023/11/5
 */
public class CbTable {

    /**
     * 表名
     */
    private String name;

    /**
     * 字段列表
     */
    private List<CbTableField> fields;

    /**
     * 唯一键
     */
    private List<CbTableUk> uks;

}
