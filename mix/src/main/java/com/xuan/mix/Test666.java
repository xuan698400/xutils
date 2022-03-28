package com.xuan.mix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.xuan.mix.utils.DateUtils;

/**
 * @author xuan
 * @since 2022/3/23
 */
public class Test666 {

    public static void main(String[] args) throws Exception {
        dealBase();
    }

    public static void dealTarget() {
        try {
            File file = new File("/Users/xuan/Desktop/111.txt");
            InputStreamReader ir = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(ir);
            String line;
            while ((line = bf.readLine()) != null) {
                String[] strs = line.split(",");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 2021);
                calendar.set(Calendar.MONTH, 2);
                String server = strs[0].trim();
                for (int i = 1; i <= 12; i++) {
                    String v = strs[i].trim();
                    calendar.add(Calendar.MONTH, 1);
                    System.out.println("('Main_Sale_Model_Decline_Target', '" + v + "', '" + DateUtils
                        .date2String(calendar.getTime(), "yyyyMM") + "', '" + server + "'),");
                }
            }
            bf.close();
            ir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dealBase() {
        try {
            File file = new File("/Users/xuan/Desktop/222");
            InputStreamReader ir = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(ir);
            String line;
            while ((line = bf.readLine()) != null) {
                String[] strs = line.split(",");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 2021);
                calendar.set(Calendar.MONTH, 2);
                String server = strs[0].trim();
                for (int i = 1; i <= 12; i++) {
                    calendar.add(Calendar.MONTH, 1);
                    System.out.println("('Main_Sale_Model_Decline_Base', '" + strs[1] + "', '" + DateUtils
                        .date2String(calendar.getTime(), "yyyyMM") + "', '" + server + "'),");
                }
            }
            bf.close();
            ir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
