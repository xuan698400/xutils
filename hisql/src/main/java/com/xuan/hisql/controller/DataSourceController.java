package com.xuan.hisql.controller;

import com.xuan.common.model.page.PageData;
import com.xuan.common.model.result.Result;
import com.xuan.hisql.controller.model.DataSourceAddDTO;
import com.xuan.hisql.controller.model.DataSourceInfoDTO;
import com.xuan.hisql.service.model.DataSourceCondition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2022/8/23
 */
@RestController
@RequestMapping("hisql/datasource/")
public class DataSourceController {

    @RequestMapping("add")
    public Result<Void> add(DataSourceAddDTO dataSourceAddDTO) {
        return Result.success(null);
    }

    @RequestMapping("modify")
    public Result<Void> modify(DataSourceAddDTO dataSourceAddDTO) {
        return Result.success(null);
    }

    @RequestMapping("remove")
    public Result<Void> remove(Long id) {
        return Result.success(null);
    }

    @RequestMapping("query")
    public Result<PageData<DataSourceInfoDTO>> query(DataSourceCondition condition) {
        return Result.success(null);
    }

}
