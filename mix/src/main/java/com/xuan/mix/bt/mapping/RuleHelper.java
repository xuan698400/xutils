package com.xuan.mix.bt.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuan
 * @since 2021/9/15
 */
public class RuleHelper {

    public static List<String> split(String rule) {
        if (null == rule || rule.trim().length() == 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(rule.split("[.]"));
    }

    public static String checkRule(List<String> ruleList) {
        if (null == ruleList) {
            return "ruleList is null";
        }
        if (ruleList.isEmpty()) {
            return "ruleList is empty";
        }

        for (String ruleItem : ruleList) {
            if (null == ruleItem) {
                return "ruleItem has null";
            }
            if (ruleItem.trim().length() == 0) {
                return "ruleItem has empty";
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(RuleHelper.split(null));
        System.out.println(RuleHelper.split(""));
        System.out.println(RuleHelper.split(" "));
        System.out.println(RuleHelper.split("a.b.c"));
        System.out.println(RuleHelper.split(".b.c"));
        System.out.println(RuleHelper.split("a..c"));
        System.out.println(RuleHelper.split("a. .c"));

        System.out.println(checkRule(RuleHelper.split(null)));
        System.out.println(checkRule(RuleHelper.split("")));
        System.out.println(checkRule(RuleHelper.split(" ")));
        System.out.println(checkRule(RuleHelper.split("a.b.c")));
        System.out.println(checkRule(RuleHelper.split(".b.c")));
        System.out.println(checkRule(RuleHelper.split("a..c")));
        System.out.println(checkRule(RuleHelper.split("a. .c")));
    }

}
