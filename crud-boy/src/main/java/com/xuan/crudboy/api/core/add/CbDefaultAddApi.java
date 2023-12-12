package com.xuan.crudboy.api.core.add;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xuan.crudboy.CrudBoy;
import com.xuan.crudboy.api.CbResult;
import com.xuan.crudboy.api.core.BaseCbApiImpl;
import com.xuan.crudboy.config.CbConfigManager;
import com.xuan.crudboy.config.model.CbTableConfig;
import com.xuan.crudboy.config.model.CbTableFieldConfig;
import com.xuan.crudboy.domain.CbSqlParams;
import com.xuan.crudboy.domain.CbTable;
import com.xuan.crudboy.domain.CbTableField;
import com.xuan.crudboy.exception.CbAssert;

/**
 * @author xuan
 * @since 2023/11/10
 */
public class CbDefaultAddApi extends BaseCbApiImpl implements CbAddApi {

    @Override
    public CbResult<Long> add(CbAddRequest request) {
        CbAssert.notNull(request, "request不能为空");
        CbAssert.notEmpty(request.getApiCode(), "apiCode不能为空");
        CbAssert.notEmpty(request.getData(), "data不能为空");

        CbConfigManager cbConfigManager = CrudBoy.getInstance().getConfigManager();
        CbTableConfig tableConfig = cbConfigManager.getTableConfig(request.getApiCode());

        List<CbTableFieldConfig> fieldConfigList = tableConfig.getFieldConfigs();
        Map<String, String> data = request.getData();
        List<CbTableField> fieldList = new ArrayList<>();
        fieldConfigList.forEach(fieldConfig -> {
            CbTableField field = CbTableField.of(fieldConfig, data.get(fieldConfig.getName()));
            fieldList.add(field);
        });

        CbTable table = CbTable.of(tableConfig, fieldList);

        CbSqlParams sqlParams = table.buildInsertSqlParams();

        return null;
    }

}
