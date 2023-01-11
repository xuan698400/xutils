package com.xuan.moho.sql.orm.resultsetmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuan.moho.sql.executer.ResultSetMapping;

/**
 * @author xuan
 * @since 2023/1/11
 */
public class StringResultSetMapping implements ResultSetMapping<String> {

    @Override
    public String extract(ResultSet rs) throws SQLException {
        return rs.getString(1);
    }

}
