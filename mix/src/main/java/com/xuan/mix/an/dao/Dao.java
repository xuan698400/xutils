package com.xuan.mix.an.dao;

import java.util.List;

import com.xuan.mix.an.common.model.page.PageData;
import com.xuan.mix.an.common.model.page.PageQuery;
import com.xuan.mix.an.dao.common.DataModel;
import com.xuan.mix.an.dao.sql.SqlCreator;

/**
 * @author xuan
 * @since 2021/11/5
 */
public interface Dao {

    /**
     * 插入数据，并返回自动生产的ID
     *
     * @param dataModel 数据模型
     * @return 返回自动生产的ID
     */
    long insertBackId(DataModel dataModel);

    /**
     * 插入数据
     *
     * @param dataModel 数据模型
     * @return 成功返回1
     */
    int insert(DataModel dataModel);

    /**
     * 更新数据（根据主键更新）
     *
     * @param dataModel 数据模型
     * @return 成功返回1
     */
    int update(DataModel dataModel);

    /**
     * 删除数据（根据设置的值拼装条件删除）
     *
     * @param dataModel 数据模型
     * @return 成功返回1
     */
    int delete(DataModel dataModel);

    /**
     * 更新SQL（当上面的条件无法满足时，可以使用该方法）
     *
     * @param sqlCreator SQL
     * @return 成功返回1
     */
    int update(SqlCreator sqlCreator);

    /**
     * 查找数据（根据设置的值拼装条件查询）
     *
     * @param dataModel   数据模型
     * @param elementType 返回的类Class对象
     * @param <T>         返回的类范型
     * @return 数据列表
     */
    <T extends DataModel> List<T> select(DataModel dataModel, Class<T> elementType);

    /**
     * 查找数据（使用SqlCreator自己拼接SQL）
     *
     * @param creator     SqlCreator实例
     * @param elementType 返回的类Class对象
     * @param <T>         返回的类范型
     * @return 数据列表
     */
    <T extends DataModel> List<T> select(SqlCreator creator, Class<T> elementType);

    /**
     * 统计数据库条数
     *
     * @param sqlCreator SqlCreator实例
     * @return 条数
     */
    Long count(SqlCreator sqlCreator);

    /**
     * 分页查询
     *
     * @param creator     SqlCreator实例
     * @param pageQuery   分页查询条件
     * @param elementType 返回的类Class对象
     * @param <T>         返回的类范型
     * @return 分页数据，含总条数
     */
    <T extends DataModel> PageData<T> selectPage(SqlCreator creator, PageQuery pageQuery, Class<T> elementType);
}
