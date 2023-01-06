package com.xuan.hisql.repository.contertor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.xuan.hisql.service.model.ConnectStatus;
import com.xuan.hisql.service.model.DataSourceType;
import com.xuan.hisql.common.JsonUtils;
import com.xuan.hisql.repository.model.DataSourcePO;
import com.xuan.hisql.service.model.DataSource;
import com.xuan.hisql.service.model.DataSourceFeatureMySql;
import com.xuan.mix.an.common.utils.CollectionUtils;

/**
 * @author xuan
 * @since 2022/8/25
 */
public class DataSourceDoToPoConvertor {

    public static DataSource toDomain(DataSourcePO dataSourcePo) {
        if (dataSourcePo == null) {
            return null;
        }
        DataSource dataSource = new DataSource();
        dataSource.setId(dataSourcePo.getId());
        dataSource.setModifyTime(dataSourcePo.getModifyTime());
        dataSource.setCreateTime(dataSourcePo.getCreateTime());
        dataSource.setName(dataSourcePo.getName());
        dataSource.setDescription(dataSourcePo.getDescription());
        dataSource.setConnectStatus(ConnectStatus.of(dataSourcePo.getConnectStatus()));
        dataSource.setDataSourceType(DataSourceType.of(dataSourcePo.getDatasourceType()));

        if (dataSource.getDataSourceType().eq(DataSourceType.MYSQL)) {
            dataSource.setDataSourceFeature(
                JsonUtils.toObject(dataSourcePo.getFeature(), DataSourceFeatureMySql.class));
        }
        return dataSource;
    }

    public static List<DataSource> toDomainList(List<DataSourcePO> dataSourcePoList) {
        if (CollectionUtils.isEmpty(dataSourcePoList)) {
            return new ArrayList<>();
        }
        return dataSourcePoList.stream().map(DataSourceDoToPoConvertor::toDomain).filter(Objects::nonNull).collect(
            Collectors.toList());
    }

    public static DataSourcePO toPo(DataSource dataSource) {
        if (dataSource == null) {
            return null;
        }
        DataSourcePO dataSourcePo = new DataSourcePO();
        dataSourcePo.setId(dataSource.getId());
        dataSourcePo.setName(dataSource.getName());
        dataSourcePo.setDescription(dataSource.getDescription());
        dataSourcePo.setModifyTime(dataSource.getModifyTime());
        dataSourcePo.setCreateTime(dataSource.getCreateTime());

        if (null != dataSource.getDataSourceType().getCode()) {
            dataSourcePo.setDatasourceType(dataSource.getDataSourceType().getCode());
        }

        if (null != dataSource.getConnectStatus()) {
            dataSourcePo.setConnectStatus(dataSource.getConnectStatus().getCode());
        }

        if (null != dataSource.getDataSourceType() && dataSource.getDataSourceType().eq(DataSourceType.MYSQL)) {
            dataSourcePo.setFeature(JsonUtils.toString(dataSource.getDataSourceFeature()));
        }

        return dataSourcePo;
    }

    public static List<DataSourcePO> toPoList(List<DataSource> dataSourceList) {
        if (CollectionUtils.isEmpty(dataSourceList)) {
            return new ArrayList<>();
        }
        return dataSourceList.stream().map(DataSourceDoToPoConvertor::toPo).filter(Objects::nonNull).collect(
            Collectors.toList());
    }

}
