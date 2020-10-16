package com.xuan.hazy.model.domain;

import java.util.Date;
import java.util.Map;

import lombok.Data;

/**
 * @author xuan
 * @since 2020/3/29
 */
@Data
public class DataLine {

    /**
     * 数据唯一主键
     */
    private String dataId;

    /**
     * 数据
     */
    private Map<String, DataLineField> fieldMap;

    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModify;

}
