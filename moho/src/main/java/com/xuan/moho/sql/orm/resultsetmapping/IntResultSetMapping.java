package com.xuan.moho.sql.orm.resultsetmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuan.moho.sql.executer.ResultSetMapping;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class IntResultSetMapping implements ResultSetMapping<Integer> {

    @Override
    public Integer extract(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }

}
