package com.xuan.mix.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @author xuan
 * @since 2020/1/17
 */
public class TestExcel {

    private final static int from = 1;
    private final static int end = 12;
    private final static Set<String> set = new HashSet<>();

    @Test
    public void testHashCode(){
        String dddd = "dddfdfd";
        System.out.println(dddd.hashCode());

        String ttttt = "123456";
        System.out.println(ttttt.hashCode());
    }

    @Test
    public void test() {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        String filePath = "/Users/xuan/Desktop/files/项目/2020/ebnp/right.xlsx";
        String columns[] = {"id", "group_id", "seller_id", "channel_type", "status", "legal_document", "start_time",
            "end_time", "review_id", "review_status", "fail_reason", "gmt_modify", "modifier", "gmt_create", "creator",
            "data_legal_document"};
        wb = readExcel(filePath);
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String)getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }

        //遍历解析出来的list
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, String> map : list) {
            if (i >= from && i <= end) {
                if (i > from) {
                    sb.append(",");
                }
                sb.append(deal(map));
            }
            i++;
        }
        sb.append("]");

        System.out.println(sb.toString());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (String s : set) {
            System.out.println(s);
        }

    }

    private static String deal(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(map.get("id"));
        sb.append(bd(map, "group_id"));
        sb.append(bd(map, "seller_id"));
        sb.append(bd(map, "channel_type"));
        sb.append(bd(map, "status"));
        sb.append(bd(map, "legal_document"));
        sb.append(bd(map, "start_time"));
        sb.append(bd(map, "end_time"));
        sb.append(bd(map, "review_id"));
        sb.append(bd(map, "review_status"));
        sb.append(bd(map, "fail_reason"));
        sb.append(bd(map, "gmt_modify"));
        sb.append(bd(map, "modifier"));
        sb.append(bd(map, "gmt_create"));
        sb.append(bd(map, "creator"));
        sb.append(be(map, "data_legal_document"));
        sb.append("\"");
        set.add(map.get("group_id"));
        return sb.toString();
    }

    private static String bd(Map<String, String> map, String key) {
        String v = map.get(key);
        if (null == v) {
            return "#";
        }
        return "#" + v;
    }

    private static String be(Map<String, String> map, String key) {
        String v = map.get(key);
        if (null == v || v.trim().length() == 0) {
            return "#-";
        }
        return "#" + v;
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
