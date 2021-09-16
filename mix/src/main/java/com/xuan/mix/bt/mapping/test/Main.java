package com.xuan.mix.bt.mapping.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xuan.mix.bt.mapping.DefaultMapping;
import com.xuan.mix.bt.mapping.Mapping;
import com.xuan.mix.bt.mapping.MappingConfig;
import com.xuan.mix.bt.mapping.MappingRule;
import com.xuan.mix.bt.mapping.RuleHelper;

/**
 * @author xuan
 * @since 2021/9/14
 */
public class Main {

    private static MappingConfig config = new MappingConfig();

    private static Data data = new Data();

    private static Mapping mapping = new DefaultMapping();

    static {
        List<MappingRule> ruleList = new ArrayList<>();

        MappingRule rule1 = new MappingRule();
        rule1.setFromObjAttrLevel(RuleHelper.split("num"));
        rule1.setToObjAttrLevel(RuleHelper.split("toNum"));
        ruleList.add(rule1);

        MappingRule rule2 = new MappingRule();
        rule2.setFromObjAttrLevel(RuleHelper.split("sNum"));
        rule2.setToObjAttrLevel(RuleHelper.split("toSNum"));
        ruleList.add(rule2);

        MappingRule rule3 = new MappingRule();
        rule3.setFromObjAttrLevel(RuleHelper.split("dataItem.name"));
        rule3.setToObjAttrLevel(RuleHelper.split("dataItem.name"));
        ruleList.add(rule3);

        config.setRuleList(ruleList);

        //
        List<DataItem> dataItemList = new ArrayList<>();

        DataItem dataItem1 = new DataItem();
        dataItem1.setName("ddd");
        dataItemList.add(dataItem1);

        DataItem dataItem2 = new DataItem();
        dataItem2.setName("222");
        dataItemList.add(dataItem2);

        data.setNum(1);
        data.setsNum(2);
        data.setDataItem(dataItem1);
        data.setDataItemList(dataItemList);
    }

    public static void main(String[] args) {
        Map<String, Object> toMap = mapping.mapping(data, config);
        System.out.println(toMap);
    }

}
