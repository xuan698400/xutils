package com.xuan.mix.bt.jijin.prophet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuan
 * @since 2022/3/13
 */
public class TxtUtils {

    public static List<Data> readData2(String fileName) {
        List<Data> dataList = new ArrayList<>();
        List<String> list = readStr(fileName);
        for (String str : list) {
            if (str.trim().length() == 0) {
                continue;
            }

            Data data = new Data();
            data.date = str.split("\t")[0];
            data.price = Double.valueOf(str.split("\t")[1]);
            dataList.add(data);
        }
        return dataList;
    }

    public static List<Data> readData(String fileName) {
        List<Data> dataList = new ArrayList<>();
        List<String> list = readStr(fileName);
        for (String str : list) {
            if (str.trim().length() == 0) {
                continue;
            }

            Data data = new Data();
            data.date = str.split(",")[0];
            data.price = Double.valueOf(str.split(",")[1]);
            dataList.add(data);
        }
        return dataList;
    }

    public static List<String> readStr(String fileName) {
        List<String> list = new ArrayList<>();
        try {
            File file = new File(
                "/Users/xuan/Desktop/code/github/xutils/mix/src/main/java/com/xuan/mix/bt/jijin/prophet/" + fileName
                    + ".txt");
            InputStreamReader ir = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(ir);
            String str;
            while ((str = bf.readLine()) != null) {
                list.add(str);
            }
            bf.close();
            ir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
