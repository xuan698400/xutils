package com.xuan.xutils.sql.ddl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xuan.xutils.sql.common.SQLSyntax;
import com.xuan.xutils.sql.ddl.spec.ColumnSpec;
import com.xuan.xutils.sql.ddl.spec.TableSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeBigIntSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeCharSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeDatetimeSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeDecimalSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeIntSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeLongTextSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeTextSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeTinyIntSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeTinyTextSpec;
import com.xuan.xutils.sql.ddl.spec.columntype.ColumnTypeVarcharSpec;
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
        ColumnSpec bigIntT = new ColumnSpec();
        bigIntT.setName("bigint_t");
        bigIntT.setAutoIncrement(true);
        bigIntT.setColumnType(ColumnTypeBigIntSpec.of());
        bigIntT.setComment("bigint_t_描述");
        bigIntT.setNotNull(true);
        bigIntT.setUnsigned(true);

        ColumnSpec charT = new ColumnSpec();
        charT.setName("char_t");
        charT.setComment("char_t_描述");
        charT.setColumnType(ColumnTypeCharSpec.of(32));

        ColumnSpec createTimeT = new ColumnSpec();
        createTimeT.setName("create_time_t");
        createTimeT.setComment("create_time_t_描述");
        createTimeT.setColumnType(ColumnTypeDatetimeSpec.of());

        ColumnSpec decimalT = new ColumnSpec();
        decimalT.setName("decimal_t");
        decimalT.setComment("decimal_t_描述");
        decimalT.setColumnType(ColumnTypeDecimalSpec.of(7, 4));

        ColumnSpec intT = new ColumnSpec();
        intT.setName("int_t");
        intT.setComment("int_t_描述");
        intT.setColumnType(ColumnTypeIntSpec.of());

        ColumnSpec longTextT = new ColumnSpec();
        longTextT.setName("long_text_t");
        longTextT.setComment("long_text_t_描述");
        longTextT.setColumnType(ColumnTypeLongTextSpec.of());

        ColumnSpec textT = new ColumnSpec();
        textT.setName("text_t");
        textT.setComment("text_t_描述");
        textT.setColumnType(ColumnTypeTextSpec.of());

        ColumnSpec tinyIntT = new ColumnSpec();
        tinyIntT.setName("tiny_int_t");
        tinyIntT.setColumnType(ColumnTypeTinyIntSpec.of());

        ColumnSpec tinyTextT = new ColumnSpec();
        tinyTextT.setName("tiny_text_t");
        tinyTextT.setColumnType(ColumnTypeTinyTextSpec.of());

        ColumnSpec varcharT = new ColumnSpec();
        varcharT.setName("varchar_t");
        varcharT.setColumnType(ColumnTypeVarcharSpec.of(512));

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
        uniqueKeysList.add(newList(charT, decimalT));
        uniqueKeysList.add(newList(decimalT, intT));

        List<List<ColumnSpec>> keysList = new ArrayList<>();
        keysList.add(newList(varcharT, textT));
        keysList.add(newList(charT));

        TableSpec tableSpec = new TableSpec();
        tableSpec.setName("ddl_table_test");
        tableSpec.setColumnList(columnList);
        tableSpec.setPrimaryKey(bigIntT);
        tableSpec.setUniqueKeysList(uniqueKeysList);
        tableSpec.setKeysList(keysList);
        tableSpec.setCharset("utf-8");
        tableSpec.setComment("ddl测试表");
        return tableSpec.createTableSQL(SQLSyntax.MYSQL);
    }

    private static <E> List<E> newList(E... elements) {
        List<E> list = new ArrayList<>();
        Collections.addAll(list, elements);
        return list;
    }

}
