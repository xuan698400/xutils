package com.xuan.mix.bt.etlflow.json2model;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xuan.mix.bt.etlflow.model.EtlFlow;
import com.xuan.mix.bt.etlflow.model.node.SelectNode;
import com.xuan.mix.bt.etlflow.model.node.TableNode;

/**
 * @author xuan
 * @since 2022/3/17
 */
public class DefaultJsonToModelHandler implements JsonToModelHandler {

    @Override
    public EtlFlow toModel(String json) {

        EtlFlow etlFlow = new EtlFlow();

        EtlFlowJsonObj etlFlowJsonObj = JSON.parseObject(json, EtlFlowJsonObj.class);
        etlFlow.setId(etlFlowJsonObj.getId());
        etlFlow.setName(etlFlowJsonObj.getName());
        etlFlow.setFlowNodeList(new ArrayList<>());

        for (String nodeStr : etlFlowJsonObj.getFlowNodes()) {
            JSONObject jsonObject = JSON.parseObject(nodeStr);
            if ("TABLE".equals(jsonObject.getString("nodeType"))) {
                etlFlow.getFlowNodeList().add(JSON.parseObject(nodeStr, TableNode.class));
            } else if ("SELECT".equals(jsonObject.getString("nodeType"))) {
                etlFlow.getFlowNodeList().add(JSON.parseObject(nodeStr, SelectNode.class));
            }
        }

        return etlFlow;
    }

    public static void main(String[] args) {
        EtlFlowJsonObj etlFlowJsonObj = JSON.parseObject(test_json, EtlFlowJsonObj.class);
        for (String d : etlFlowJsonObj.getFlowNodes()) {
            System.out.println(d);
        }
    }

    public static final String test_json = "{\n"
        + "  id:\"1\",\n"
        + "  name:\"云网络成本分摊\",\n"
        + "  flowNodes:[\n"
        + "    {\n"
        + "      id:\"1\",\n"
        + "      name:\"阿里云网络月数据\",\n"
        + "      nodeType:\"TABLE\",\n"
        + "      tableName:\"ais_dcos.dcos_charge_aliyun_network_billing_monthly\",\n"
        + "      to:\"2\"\n"
        + "    },\n"
        + "    {\n"
        + "      id:\"2\",\n"
        + "      name:\"进行过滤操作\",\n"
        + "      nodeType:\"SELECT\",\n"
        + "      column:\"dcos_id,app_name,product_line_full_name,tenant_id,tenant_name\",\n"
        + "      where:\"bu_id = 6\"\n"
        + "    }\n"
        + "  ]\n"
        + "}";
}
