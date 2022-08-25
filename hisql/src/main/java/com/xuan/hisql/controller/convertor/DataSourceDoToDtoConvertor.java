package com.xuan.hisql.controller.convertor;

import java.util.List;

import com.xuan.common.utils.DateUtils;
import com.xuan.hisql.controller.model.DataSourceAddDTO;
import com.xuan.hisql.controller.model.DataSourceInfoDTO;
import com.xuan.hisql.controller.model.DataSourceModifyDTO;
import com.xuan.hisql.controller.model.MySqlInfo;
import com.xuan.hisql.service.model.DataSource;
import com.xuan.hisql.service.model.DataSourceFeatureMySql;
import com.xuan.hisql.service.model.DataSourceType;

/**
 * @author xuan
 * @since 2022/8/25
 */
public class DataSourceDoToDtoConvertor {

    public static DataSource toAddDomain(DataSourceAddDTO addDto) {
        if (addDto == null) {
            return null;
        }
        DataSource dataSource = new DataSource();
        dataSource.setName(addDto.getName());
        dataSource.setDescription(addDto.getDescription());
        dataSource.setDataSourceType(DataSourceType.of(addDto.getDataSourceType()));
        if (DataSourceType.MYSQL.eq(dataSource.getDataSourceType())) {
            dataSource.setDataSourceFeature(toDataSourceFeatureMySql(addDto));
        }
        return dataSource;
    }

    public static DataSource toModifyDomain(DataSourceModifyDTO modifyDto) {
        if (modifyDto == null) {
            return null;
        }
        DataSource dataSource = new DataSource();
        dataSource.setId(modifyDto.getId());
        dataSource.setName(modifyDto.getName());
        dataSource.setDescription(modifyDto.getDescription());
        dataSource.setDataSourceType(DataSourceType.of(modifyDto.getDataSourceType()));
        if (DataSourceType.MYSQL.eq(dataSource.getDataSourceType())) {
            dataSource.setDataSourceFeature(toDataSourceFeatureMySql(modifyDto));
        }
        return dataSource;
    }

    public static DataSourceFeatureMySql toDataSourceFeatureMySql(MySqlInfo mySqlInfo) {
        if (mySqlInfo == null) {
            return null;
        }
        DataSourceFeatureMySql dataSourceFeatureMySql = new DataSourceFeatureMySql();
        dataSourceFeatureMySql.setUrl(mySqlInfo.getUrl());
        dataSourceFeatureMySql.setUsername(mySqlInfo.getUsername());
        dataSourceFeatureMySql.setPassword(mySqlInfo.getPassword());
        return dataSourceFeatureMySql;
    }

    public static DataSourceInfoDTO toInfoDto(DataSource dataSource) {
        if (dataSource == null) {
            return null;
        }
        DataSourceInfoDTO dataSourceInfoDTO = new DataSourceInfoDTO();
        dataSourceInfoDTO.setId(dataSource.getId());
        dataSourceInfoDTO.setName(dataSource.getName());
        dataSourceInfoDTO.setDescription(dataSource.getDescription());
        dataSourceInfoDTO.setDataSourceType(dataSource.getDataSourceType().getCode());
        dataSourceInfoDTO.setModifyTime(DateUtils.date2String(dataSource.getModifyTime()));

        if (DataSourceType.MYSQL.eq(dataSource.getDataSourceType())) {
            DataSourceFeatureMySql dataSourceFeatureMySql = (DataSourceFeatureMySql)dataSource.getDataSourceFeature();
            dataSourceInfoDTO.setUrl(dataSourceFeatureMySql.getUrl());
            dataSourceInfoDTO.setUsername(dataSourceFeatureMySql.getUsername());
            dataSourceInfoDTO.setPassword(dataSourceFeatureMySql.getPassword());
        }
        return dataSourceInfoDTO;
    }

    public static List<DataSourceInfoDTO> toInfoDtoList(List<DataSource> dataSourceList) {
        return null;
    }

}
