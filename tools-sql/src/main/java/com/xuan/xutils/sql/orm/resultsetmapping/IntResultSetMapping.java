package com.xuan.xutils.sql.orm.resultsetmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuan.xutils.sql.executer.ResultSetMapping;

/**
 * 返回int数字结果映射实现
 *
 * @author xuan
 * @since 2023/1/11
 */
public class IntResultSetMapping implements ResultSetMapping<Integer> {

    @Override
    public Integer extract(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }

}
