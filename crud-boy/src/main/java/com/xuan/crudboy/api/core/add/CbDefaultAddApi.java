package com.xuan.crudboy.api.core.add;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xuan.crudboy.CrudBoy;
import com.xuan.crudboy.api.CbResult;
import com.xuan.crudboy.api.core.BaseCbApiImpl;
import com.xuan.crudboy.config.CbConfigManager;
import com.xuan.crudboy.config.model.CbTableConfig;
import com.xuan.crudboy.domain.CbSqlParams;
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

        //
        List<CbTableField> fieldList = new ArrayList<>();
        Map<String, String> data = request.getData();
        data.forEach((fieldName, value) -> {

        });

        return null;
    }

    public CbSqlParams build(List<CbTableField> fieldList) {

        //List<Object> valueList = new ArrayList<>();
        //StringBuilder sql = new StringBuilder("INSERT INTO " + config.getName() + "(");
        //int paramsSize = 0;
        //
        //for (CbTableField field : fields) {
        //    Object value = field.getValue();
        //    if (null == value) {
        //        continue;
        //    }
        //    sql.append(CbCamelUtils.camelToUnderline(field.getConfig().getName()));
        //    sql.append(",");
        //    valueList.add(value);
        //    paramsSize++;
        //}
        //
        //sql.deleteCharAt(sql.length() - 1);
        //sql.append(") VALUES(");
        //for (int i = 0; i < paramsSize; i++) {
        //    sql.append("?,");
        //}
        //sql.deleteCharAt(sql.length() - 1);
        //sql.append(")");
        //
        //CbSqlParams sqlParams = new CbSqlParams();
        //sqlParams.setSql(sql.toString());
        //sqlParams.setParams(valueList);
        //return sqlParams;
        return null;
    }

}
