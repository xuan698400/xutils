package com.xuan.moho.sql.ddl.spec;

import com.xuan.moho.sql.common.SqlSyntax;

/**
 * 是否可以进行补充0操作
 * 例如：int(11), 那么当存入的数字不足11位时，用0补充，例如存入是1，那么数字将会是00000000001
 * 当然还需要执行zerofill配合一起使用
 *
 * @author xuan
 * @since 2023/1/6
 */
public abstract class BaseColumnTypeZeroFillSpec implements ColumnTypeSpec {

    private Integer zeroFillNum;

    public Integer getZeroFillNum() {
        return zeroFillNum;
    }

    public void setZeroFillNum(Integer zeroFillNum) {
        this.zeroFillNum = zeroFillNum;
    }

    @Override
    public String buildSqlFragment(SqlSyntax sqlSyntax) {
        return doBuildSqlFragment(sqlSyntax) + (null != zeroFillNum ? String.format("(%s) zerofill", zeroFillNum) : "");
    }

    abstract protected String doBuildSqlFragment(SqlSyntax sqlSyntax);

}
