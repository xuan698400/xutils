package com.xuan.hitools.hisql;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuan
 * @since 2022/8/30
 */
@RestController
@RequestMapping("hitools/hisql/")
public class HiSqlController {

    @Resource
    private DataSourceHelper dataSourceHelper;

    @RequestMapping("dataSources")
    public Map<String, Object> dataSources() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", dataSourceHelper.getAllDataSource());
        return result;
    }

    @RequestMapping("exec")
    public Map<String, Object> exec(String code, String sql) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", dataSourceHelper.getJdbcTemplateByCode(code).queryForList(sql));
        return result;
    }

}
