//package com.xuan.mix.excel;
//
//import java.io.File;
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.xuan.mix.an.common.utils.StringUtils;
//import com.xuan.mix.file.FileUtils;
//
///**
// * @author xuan
// * @since 2023/3/23
// */
//public class DiffTest {
//
//    public static void main(String[] args) throws Exception {
//        String compute = FileUtils.readFileToString(new File("/Users/xuan/Downloads/bfttt/1.txt"), "utf-8");
//        String check = FileUtils.readFileToString(new File("/Users/xuan/Downloads/bfttt/2.txt"), "utf-8");
//
//        Map<String, Double> computeMap = new HashMap<>();
//        List<String> computeList = StringUtils.split(compute, "\n");
//        computeList.forEach(v -> {
//            String[] vs = v.split("\t");
//            computeMap.put(vs[0] + "_" + vs[1], Double.valueOf(new BigDecimal(vs[2]).toPlainString()));
//        });
//
//        Map<String, Double> checkMap = new HashMap<>();
//        List<String> checkList = StringUtils.split(check, "\n");
//        checkList.forEach(v -> {
//            String[] vs = v.split(",");
//            checkMap.put(vs[1] + "_" + vs[0], Double.valueOf(new BigDecimal(vs[2]).toPlainString()));
//        });
//
//        //System.out.println(computeMap);
//        //System.out.println(checkMap);
//
//        checkMap.forEach((k, v) -> {
//            Double computeDouble = computeMap.get(k);
//            if (null == computeDouble) {
//                System.out.println(k + ">>>>>错误：没有找到computeDouble");
//                return;
//            }
//            if (v - computeDouble == 0) {
//                //System.out.println(k + ">>>>>结果完成一致");
//                return;
//            }
//
//            if (Math.abs(v - computeDouble) < 200000) {
//                //System.out.println(k + ">>>>>有误差但是结果可接受");
//                return;
//            }
//
//            System.out.println(k + ">>>>>比对不一致。计算值：" + computeDouble + "，手算值：" + v);
//        });
//
//    }
//
//}
