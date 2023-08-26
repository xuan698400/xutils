package com.xuan.xutils.sql.orm.sqlparams;

import com.xuan.xutils.sql.orm.model.DataModel;

/**
 * @author xuan
 * @since 2022/1/25
 */
public interface SQLParamsBuilder {

    /**
     * Sql构建器（根据数据模型进行构建SQL）
     *
     * @param dataModel 数据模型
     * @return SQL模型，含SQL以及动态参数
     */
    SQLParams getSQLParams(DataModel dataModel);
}
