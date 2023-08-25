package com.xuan.mix.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 预算
 *
 * @author xuan
 * @since 2020/1/17
 */
public class TestExcel6 {

    private final static String filePath = "/Users/xuan/Downloads/123321.xlsx";

    private final static String fPath = "/Users/xuan/Downloads/123321.xlsx";

    @Test
    public void test() {
        Workbook wb = readExcel(filePath);
        Sheet sheet = wb.getSheetAt(0);

        List<Map<String, String>> dataList = new ArrayList<>();

        int rowNum = 10;

        for (int i = 4; i < rowNum; i++) {
            Map<String, String> map = new HashMap<>();
            Row row = sheet.getRow(i);
            map.put("c1", (String)getCellFormatValue(row.getCell(1)));
            map.put("c2", (String)getCellFormatValue(row.getCell(2)));
            map.put("c3", (String)getCellFormatValue(row.getCell(3)));
            map.put("c4", (String)getCellFormatValue(row.getCell(4)));
            dataList.add(map);
        }
        System.out.println(JSON.toJSONString(dataList));
    }

    private static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}