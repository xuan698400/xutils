package com.xuan.moho.sql.ddl;

import java.util.ArrayList;
import java.util.List;

import com.xuan.moho.base.utils.Lists;
import com.xuan.moho.sql.common.SQLSyntax;
import com.xuan.moho.sql.ddl.spec.ColumnSpec;
import com.xuan.moho.sql.ddl.spec.TableSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeBigIntSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeCharSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeDatetimeSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeDecimalSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeIntSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeLongTextSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeTextSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeTinyIntSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeTinyTextSpec;
import com.xuan.moho.sql.ddl.spec.columntype.ColumnTypeVarcharSpec;
import org.junit.Test;

/**
 * @author xuan
 * @since 2023/1/6
 */
public class DdlBuilderTest {

    @Test
    public void testCreateSql() {
        System.out.println(getCreateTableSql());
    }

    public String getCreateTableSql() {
        ColumnSpec bigIntT = ColumnSpec.builder()
            .name("bigint_t")
            .autoIncrement(true)
            .columnType(ColumnTypeBigIntSpec.of())
            .comment("bigint_t_描述")
            .notNull(true)
            .unsigned(true)
            .build();

        ColumnSpec charT = ColumnSpec.builder()
            .name("char_t").comment("char_t_描述").columnType(ColumnTypeCharSpec.of(32)).build();

        ColumnSpec createTimeT = ColumnSpec.builder()
            .name("create_time_t").comment("create_time_t_描述").columnType(ColumnTypeDatetimeSpec.of()).build();

        ColumnSpec decimalT = ColumnSpec.builder()
            .name("decimal_t").comment("decimal_t_描述").columnType(ColumnTypeDecimalSpec.of(7, 4)).build();

        ColumnSpec intT = ColumnSpec.builder()
            .name("int_t").comment("int_t_描述").columnType(ColumnTypeIntSpec.of()).build();

        ColumnSpec longTextT = ColumnSpec.builder()
            .name("long_text_t").comment("long_text_t_描述").columnType(ColumnTypeLongTextSpec.of()).build();

        ColumnSpec textT = ColumnSpec.builder()
            .name("text_t").comment("text_t_描述").columnType(ColumnTypeTextSpec.of()).build();

        ColumnSpec tinyIntT = ColumnSpec.builder()
            .name("tiny_int_t").columnType(ColumnTypeTinyIntSpec.of()).build();

        ColumnSpec tinyTextT = ColumnSpec.builder()
            .name("tiny_text_t").columnType(ColumnTypeTinyTextSpec.of()).build();

        ColumnSpec varcharT = ColumnSpec.builder()
            .name("varchar_t").columnType(ColumnTypeVarcharSpec.of(512)).build();

        List<ColumnSpec> columnList = new ArrayList<>();
        columnList.add(bigIntT);
        columnList.add(charT);
        columnList.add(createTimeT);
        columnList.add(decimalT);
        columnList.add(intT);
        columnList.add(longTextT);
        columnList.add(textT);
        columnList.add(tinyIntT);
        columnList.add(tinyTextT);
        columnList.add(varcharT);

        List<List<ColumnSpec>> uniqueKeysList = new ArrayList<>();
        uniqueKeysList.add(Lists.newList(charT, decimalT));
        uniqueKeysList.add(Lists.newList(decimalT, intT));

        List<List<ColumnSpec>> keysList = new ArrayList<>();
        keysList.add(Lists.newList(varcharT, textT));
        keysList.add(Lists.newList(charT));

        TableSpec tableSpec = TableSpec.builder()
            .name("ddl_table_test")
            .columnList(columnList)
            .primaryKey(bigIntT)
            .uniqueKeysList(uniqueKeysList)
            .keysList(keysList)
            .charset("utf-8")
            .comment("ddl测试表")
            .build();
        return tableSpec.createTableSQL(SQLSyntax.MYSQL);
    }

}
