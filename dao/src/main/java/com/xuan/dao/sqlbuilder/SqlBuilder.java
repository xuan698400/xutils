package com.xuan.dao.sqlbuilder;

import com.xuan.dao.common.DataModel;

/**
 * @author xuan
 * @since 2022/1/25
 */
public interface SqlBuilder {

    SqlModel getSql(DataModel dataModel);
}
